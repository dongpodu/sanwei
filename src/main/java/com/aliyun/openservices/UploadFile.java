package com.aliyun.openservices;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.aliyun.openservices.oss.OSSClient;
import com.aliyun.openservices.oss.model.ObjectMetadata;
import com.aliyun.openservices.oss.model.PutObjectResult;

public class UploadFile {

	
	public static void main(String[] args) throws IOException{
		String buketName = "sanwei";
		String keyId = "2lAfaf7Z5z22u6GS";
		String keyScret = "QEcPL7BwW71XWjNlwcgGdvfZKyVQ15";
		OSSClient client = new OSSClient(keyId,keyScret);
		
		
		//上传
		File file = new File("E://1111.jpg");
		InputStream content = new FileInputStream(file);
		ObjectMetadata meta = new ObjectMetadata();
		meta.setContentLength(file.length());
		PutObjectResult result =  client.putObject(buketName, file.getName(), content, meta);
		System.out.println(result.getETag());
		
		//展示
//		ObjectListing listing = client.listObjects(buketName);
//		for (OSSObjectSummary objectSummary : listing.getObjectSummaries()) {
//	        System.out.println(objectSummary.getKey());
//	    }
		
		//下载
//		OSSObject ossObj = client.getObject(buketName,keyId);
//		InputStream input =  ossObj.getObjectContent();
//		FileOutputStream out = new FileOutputStream(new File("E://jianli"));
//		byte[] by = new byte[1024*8];
//		int i =0;
//		while((i=input.read(by))!=-1){
//			out.write(by,0,i);
//		}
//		input.close();
//		out.close();
	}
}
