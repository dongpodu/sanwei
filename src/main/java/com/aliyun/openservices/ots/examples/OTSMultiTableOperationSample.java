package com.aliyun.openservices.ots.examples;

import java.util.ArrayList;
import java.util.List;

import com.aliyun.openservices.ClientException;
import com.aliyun.openservices.ServiceException;
import com.aliyun.openservices.ots.OTSClient;
import com.aliyun.openservices.ots.OTSErrorCode;
import com.aliyun.openservices.ots.OTSException;
import com.aliyun.openservices.ots.model.ColumnValue;
import com.aliyun.openservices.ots.model.MultiDeleteRowItem;
import com.aliyun.openservices.ots.model.MultiGetRowItem;
import com.aliyun.openservices.ots.model.MultiPutRowItem;
import com.aliyun.openservices.ots.model.PrimaryKeyType;
import com.aliyun.openservices.ots.model.PrimaryKeyValue;
import com.aliyun.openservices.ots.model.RowDeleteChange;
import com.aliyun.openservices.ots.model.RowOperationStatus;
import com.aliyun.openservices.ots.model.RowPutChange;
import com.aliyun.openservices.ots.model.SingleRowQueryCriteria;
import com.aliyun.openservices.ots.model.TableMeta;

public class OTSMultiTableOperationSample {
    private static final String COLUMN_UID_NAME = "uid";
    private static final String COLUMN_NAME_NAME = "name";
    private static final String COLUMN_FLAG_NAME = "flag";
    private static final String COLUMN_ADDRESS_NAME = "address";
    private static final String COLUMN_GID_NAME = "groupid";
    private static final String tableNamePrefix = "sampleTable_";
    private static final int TABLE_COUNT = 5;

    public static void main(String args[]) {
        final String accessId = "<your access id>";
        final String accessKey = "<your access key>";
        OTSClient client = new OTSClient(accessId, accessKey); 
        
        try{
            // 创建表
            for(int i = 0; i < TABLE_COUNT; i++) {
                createTable(client, tableNamePrefix + i);
            }

            // 注意：创建表只是提交请求，OTS创建表需要一段时间。
            // 这里简单地等待30秒，请根据您的实际逻辑修改。
            Thread.sleep(30000);

            // 向多个表插入多行数据
            putMultiData(client);
            // 从多个表读取多行数据
            getMultiData(client);
            // 从多个表删除多行数据
            deleteMultiData(client);
        }catch(ServiceException e){
            System.err.println("操作失败，详情：" + e.getMessage());
            // 可以根据错误代码做出处理， OTS的ErrorCode定义在OTSErrorCode中。
            if (OTSErrorCode.QUOTA_EXHAUSTED.equals(e.getErrorCode())){
                System.err.println("超出存储配额。");
            }
            // Request ID和Host ID可以用于有问题时联系客服诊断异常。
            System.err.println("Request ID:" + e.getRequestId());
            System.err.println("Host ID:" + e.getHostId());
        }catch(ClientException e){
            // 可能是网络不好或者是返回结果有问题
            System.err.println("请求失败，详情：" + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
        finally{
            // 不留垃圾。
            try {
                for(int i = 0; i < TABLE_COUNT; i++) {
                    //deleteTable(client, tableNamePrefix + i);
                }
            } catch (ServiceException e) {
                System.err.println("删除表格失败，原因：" + e.getMessage());
                e.printStackTrace();
            } catch (ClientException e) {
                System.err.println("删除表格请求失败，原因：" + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private static void createTable(OTSClient client, String tableName)
            throws ServiceException, ClientException{
        TableMeta tableMeta = new TableMeta(tableName);
        tableMeta.addPrimaryKey(COLUMN_UID_NAME, PrimaryKeyType.INTEGER); // group id
        tableMeta.addPrimaryKey(COLUMN_NAME_NAME, PrimaryKeyType.STRING); // user id
        tableMeta.addPrimaryKey(COLUMN_FLAG_NAME, PrimaryKeyType.BOOLEAN); // user id

        client.createTable(tableMeta);

        System.out.println("表已创建");
    }

    private static void deleteTable(OTSClient client, String tableName)
            throws ServiceException, ClientException{

        //client.deleteTable(tableName);

        System.out.println("表已删除");
    }

    private static void putMultiData(OTSClient client)
            throws OTSException, ClientException{
        List<MultiPutRowItem> rowChanges = new ArrayList<MultiPutRowItem>();
        // 向TABLE_COUNT张表中插入数据， 每张表插入10行。
        for(int i = 0; i < TABLE_COUNT; ++i)
        {
            for(int j = 0; j < 10; ++j){
                
                RowPutChange rowPutChange = new RowPutChange();
                rowPutChange.addPrimaryKey(COLUMN_UID_NAME, PrimaryKeyValue.fromLong(1));
                rowPutChange.addPrimaryKey(COLUMN_NAME_NAME, PrimaryKeyValue.fromString("Name" + String.format("%03d", j)));
                rowPutChange.addPrimaryKey(COLUMN_FLAG_NAME, PrimaryKeyValue.fromBoolean(true));
                rowPutChange.addAttributeColumn(COLUMN_ADDRESS_NAME, ColumnValue.fromString("HangZhou"));
                rowPutChange.addAttributeColumn(COLUMN_GID_NAME, ColumnValue.fromLong(1));
                MultiPutRowItem rowItem = new MultiPutRowItem(tableNamePrefix + i, rowPutChange);
                rowChanges.add(rowItem);
            }
        }
        // multiPutRow接口会返回一个结果集， 结果集中包含的结果个数与插入的行数相同。 结果集中的结果不一定都是成功， 用户需要自己对不成功的操作进行重试。
        List<RowOperationStatus> results = client.multiPutRow(rowChanges);
        List<MultiPutRowItem> failedRows = new ArrayList<MultiPutRowItem>();
        int succeedCount = 0;
        
        int retryCount = 0;
        do {
            failedRows.clear();
            for(int i = 0; i < results.size(); i++) {
                if(!results.get(i).isSucceed()) {
                    // 操作失败， 需要放到重试列表中再次重试
                    failedRows.add(rowChanges.get(i));
                } else {
                    succeedCount++;
                }
            }
            
            if(failedRows.isEmpty() || ++retryCount > 3) { 
               break; // 如果所有操作都成功了或者重试次数达到上线， 则不再需要重试。
            }
            
            // 如果有需要重试的操作， 则稍微等待一会后再次重试， 否则继续出错的概率很高。
            try {
                Thread.sleep(100); // 100ms后继续重试
            } catch (InterruptedException e) {
                e.printStackTrace();
            } 
            results = client.multiPutRow(failedRows);
        } while(true);
        
        System.out.println(String.format("成功插入%d行数据。", succeedCount));
        System.out.println(String.format("失败插入%d行数据。", failedRows.size()));
    }

    private static void getMultiData(OTSClient client)
            throws OTSException, ClientException{
    	// test multi get row
        List<SingleRowQueryCriteria> criterias = new ArrayList<SingleRowQueryCriteria>();
        // 从每张表中查询插入的10行数据
        for(int i = 0; i < TABLE_COUNT; ++i)
        {    
            for(int j = 0; j < 10; ++j){
                SingleRowQueryCriteria criteria = new SingleRowQueryCriteria(tableNamePrefix + i);
                criteria.addPrimaryKey(COLUMN_UID_NAME, PrimaryKeyValue.fromLong(1));
                criteria.addPrimaryKey(COLUMN_NAME_NAME, PrimaryKeyValue.fromString("Name" + String.format("%03d", j)));
                criteria.addPrimaryKey(COLUMN_FLAG_NAME, PrimaryKeyValue.fromBoolean(true));
                criteria.addColumnNames(new String[] {COLUMN_NAME_NAME, COLUMN_GID_NAME, COLUMN_UID_NAME});
                criterias.add(criteria);
            }
        }
        List<MultiGetRowItem> results = client.multiGetRow(criterias);
        List<SingleRowQueryCriteria> failedRows = new ArrayList<SingleRowQueryCriteria>();
        List<MultiGetRowItem> succeedRows = new ArrayList<MultiGetRowItem>();
        
        int retryCount = 0;
        do {
            failedRows.clear();
            for(int i = 0; i < results.size(); i++) {
                if(!results.get(i).isSucceed()) {
                    // 操作失败， 需要放到重试列表中再次重试
                    failedRows.add(criterias.get(i));
                } else {
                    succeedRows.add(results.get(i));
                }
            }
            
            if(failedRows.isEmpty() || ++retryCount > 3) { 
               break; // 如果所有操作都成功了或者重试次数达到上线， 则不再需要重试。
            }
            
            // 如果有需要重试的操作， 则稍微等待一会后再次重试， 否则继续出错的概率很高。
            try {
                Thread.sleep(100); // 100ms后继续重试
            } catch (InterruptedException e) {
                e.printStackTrace();
            } 
            results = client.multiGetRow(failedRows);
        } while(true);
        
        System.out.println(String.format("查询成功%d行数据。", succeedRows.size()));
        System.out.println(String.format("查询失败%d行数据。", failedRows.size()));
    }
    

    private static void deleteMultiData(OTSClient client) {
    	List<MultiDeleteRowItem> rowChanges = new ArrayList<MultiDeleteRowItem>();
        // 向TABLE_COUNT张表中插入数据， 每张表插入10行。
        for(int i = 0; i < TABLE_COUNT; ++i)
        {
            for(int j = 0; j < 10; ++j){
                // 删除整行
                RowDeleteChange rowDeleteChange = new RowDeleteChange();
                rowDeleteChange.addPrimaryKey(COLUMN_UID_NAME, PrimaryKeyValue.fromLong(1));
                rowDeleteChange.addPrimaryKey(COLUMN_NAME_NAME, PrimaryKeyValue.fromString("Name" + String.format("%03d", j)));
                rowDeleteChange.addPrimaryKey(COLUMN_FLAG_NAME, PrimaryKeyValue.fromBoolean(true));
                MultiDeleteRowItem rowItem = new MultiDeleteRowItem(tableNamePrefix + i, rowDeleteChange);
                rowChanges.add(rowItem);
            }
        }
        // multiDeleteRow接口会返回一个结果集， 结果集中包含的结果个数与插入的行数相同。 结果集中的结果不一定都是成功， 用户需要自己对不成功的操作进行重试。
        List<RowOperationStatus> results = client.multiDeleteRow(rowChanges);
        List<MultiDeleteRowItem> failedRows = new ArrayList<MultiDeleteRowItem>();
        int succeedCount = 0;
        
        int retryCount = 0;
        do {
            failedRows.clear();
            for(int i = 0; i < results.size(); i++) {
                if(!results.get(i).isSucceed()) {
                    // 操作失败， 需要放到重试列表中再次重试
                    failedRows.add(rowChanges.get(i));
                } else {
                    succeedCount++;
                }
            }
            
            if(failedRows.isEmpty() || ++retryCount > 3) { 
               break; // 如果所有操作都成功了或者重试次数达到上线， 则不再需要重试。
            }
            
            // 如果有需要重试的操作， 则稍微等待一会后再次重试， 否则继续出错的概率很高。
            try {
                Thread.sleep(100); // 100ms后继续重试
            } catch (InterruptedException e) {
                e.printStackTrace();
            } 
            results = client.multiDeleteRow(failedRows);
        } while(true);
        
        System.out.println(String.format("成功删除%d行数据。", succeedCount));
        System.out.println(String.format("失败删除%d行数据。", failedRows.size()));
    }
}
