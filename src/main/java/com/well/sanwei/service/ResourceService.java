package com.well.sanwei.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.well.sanwei.dao.ResourceDao;
import com.well.sanwei.enu.FileType;
import com.well.sanwei.model.Resource;
import com.well.sanwei.model.User;
import com.well.sanwei.util.FileConvertor;
import com.well.sanwei.util.FileUtils;
import com.well.sanwei.util.WebPathUtil;

@Service
public class ResourceService {
	
	private static final String IMAG_SMALL="_small";
	private static final String IMAG_ORIGNAL="_original";
	private static final int SMALL_IMAGE_WIDTH=173;
	private static final int SMALL_IMAGE_HEIGHT=130;
	
	@Autowired
	private ResourceDao resourceDao;
	
	/**
	 * 上传非视频文件
	 * @param input
	 * @param fileType
	 * @return String  源文件路径
	 * @throws Exception
	 */
	public String  upload(InputStream input,FileType fileType) throws Exception{
		if(input==null || fileType==null){
			return "";
		}
		
		if(!FileUtils.checkFileType(fileType.name())){
			throw new IllegalArgumentException("上传的文件类型仅支持:"+FileUtils.listEnumFileType());
		}
		
		//保存原始文件
		String fileName = generaterFileName(fileType, true);
		String path=saveOriginalFile(fileName,input,fileType);
		String originalFilePath=WebPathUtil.getWebAppPath()+"/"+path;  //原始文件地址
		
		//若是office文档，转换为pdf，再转换为swf
		if(FileUtils.checkOffice(fileType.name())){
			FileConvertor.convertOffice2SWF(fileType, new FileInputStream(originalFilePath), fileName);
		}
		//若是pdf，转换为swf
		else if(FileType.pdf.name().equals(fileType.name())){
			FileConvertor.convertPdf2SWF(input, fileName);
		}
		//压缩后保存
		else if(FileUtils.checkImage(fileType.name())){
			String smallName = fileName.replace(IMAG_ORIGNAL, IMAG_SMALL);
			File file = new File(originalFilePath);
			FileConvertor.resizeImageAndSave(file, SMALL_IMAGE_WIDTH, SMALL_IMAGE_HEIGHT, fileType, smallName);
		}
		
		return path;
	}
	
	/**
	 * 保存原始文件
	 * @param input
	 * @param fileType
	 * @throws Exception
	 */
	private String saveOriginalFile(String fileName,InputStream input,FileType fileType) throws Exception{
		String path = FileUtils.absoluteFileTypeDir(fileType)+"/"+fileName+"."+fileType.name();
		File file = new File(path);
		if(!file.getParentFile().exists()){
			file.getParentFile().mkdir();
		}
		BufferedInputStream in = new BufferedInputStream(input);
        BufferedOutputStream out = null;
        try {
            out = new BufferedOutputStream(new FileOutputStream(file));
            byte[] bys = new byte[2 * 1024];
            int n = -1;
            while ((n = in.read(bys)) > -1) {
                out.write(bys, 0, n);
            }
            out.flush();
        }catch (Exception e) {
        	e.printStackTrace();
        }finally {
        	out.close();
        }
        return FileUtils.relativeFilePath(fileType, fileName);
	}
	
	/**
	 * 保存资源记录到数据库
	 * @param resource
	 * @param user
	 * @return
	 */
	@Transactional
	public boolean saveResource(Resource resource,User user){
		if(resource==null || user==null){
			return false;
		}
		resource.setCreatorId(user.getId());
		resource.setCreatName(user.getName());
		resource.setFileType(FileUtils.suffix(resource.getResourceUrl()));
		resource.setStatus(0);
		resourceDao.insert(resource);
		return true;
	}
	
	public Resource getResource(int id){
		return resourceDao.queryById(id);
	}
	
	/**
	 * 获取office对应的swf path
	 * @param officePath
	 * @return
	 */
	public String getSwfPath(String officePath){
		if(StringUtils.isBlank(officePath)){
			return "";
		}
		String filType = FileUtils.suffix(officePath);
		if(!FileUtils.checkOffice(filType)){
			return "";
		}
		String name = officePath.substring(officePath.lastIndexOf("/")+1,
				officePath.lastIndexOf(filType)-1);
		return FileUtils.relativeFilePath(FileType.swf, name);
	}
	
	/**
	 * 文件名(不带后缀名)
	 * @return
	 */
	private String generaterFileName(FileType fileType,boolean isOriginal){
		String name = System.currentTimeMillis()+"";
		if(FileUtils.checkImage(fileType.name())){
			if(isOriginal){
				name = name+IMAG_ORIGNAL;
			}
			else{
				name = name+IMAG_SMALL;
			}
		}
		return name;
	}
	
}
