package com.well.sanwei.util;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class WebPathUtil {
	
	private final static String TEMP_FOLDER_NAME = "download/";
	private final static String WEBAPP_NAME = "webapps";
	
	/**
	 * 获得项目的根路径
	 * @return
	 */
	public static String getContextPath(){
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		String path = webApplicationContext.getServletContext().getRealPath("/");
		return path.replaceAll("\\\\", "/");
	}
	
	/**
	 * 获得服务器webapps路径
	 * @return
	 */
	public static String getWebAppPath(){
		String rootPath = getContextPath();
		String appPath = rootPath.substring(0,rootPath.lastIndexOf("/"));
		appPath = appPath.substring(0,appPath.lastIndexOf("/"));
		return appPath;
	}
	
	/**
	 * 获得打包下载的文件夹路径
	 * @return 
	 */
	public static String getTempDownloadPath(){
		String path = getWebAppPath() + TEMP_FOLDER_NAME;
		return path;
	}
	
	/**
	 * 获得打包下载的文件夹路径
	 * @return 
	 */
	public static String getTempDownloadDir(){
		String path = System.getProperty("user.dir") ;
		path = path + "/" + WEBAPP_NAME + "/" + TEMP_FOLDER_NAME + "/";
		return path;
	}

}
