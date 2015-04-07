package com.well.sanwei.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * @author duyisong
 *
 */
public class ResourceMap{ 
	
	private static Logger log =Logger.getLogger( ResourceMap.class.getName() );
	
	private static final String EVN_FILE = "global.properties";
	
	private static final String EVN_KEY = "env";
	
	private static final String CONFIG_NAME = "config";
	
	private static String evnDir = "";

	private static Map<String, String> resourceMap = init();

	private ResourceMap(){}
	
	/**
	 * 初始化资源文件
	 * @return
	 */
	private static Map<String, String> init() {
		Map<String, String>  initMap = new HashMap<String, String>();
		evnDir = getProperties(EVN_FILE).getProperty(EVN_KEY);
		
		List<File> configs = getAllPropertiesFile(CONFIG_NAME);
		File file = new File(ResourceMap.class.getClassLoader().getResource(EVN_KEY).getFile() + "/" + evnDir + ".properties");
		if(!file.exists()){
			log.error("未找到" + evnDir + "环境对应的资源文件！");
		}
		configs.add(file);
		for(File f : configs){
			Properties prop = getProperties(f);
			propTransIntoMap(initMap,prop);
		}
		return initMap;
	}
	
	private static void propTransIntoMap(Map<String, String> initMap,
			Properties prop) {
		Set<Object> keySet = prop.keySet();
		for(Object key : keySet){
			String propKey = ((String) key).trim();
			String propValue = prop.getProperty((String) key).trim();
			initMap.put(propKey, propValue);
		}
	}

	/**
	 * 返回资源目录下所有文件  默认后缀为 .properties
	 * @param dir
	 * @return
	 */
	private static List<File> getAllPropertiesFile(String dir) {
		return getAllPropertiesFile(dir, ".properties");
	}
	
	
	/**
	 * 返回资源目录dir中所有属性文件
	 * @param dir
	 * @return
	 */
	private static List<File> getAllPropertiesFile(String dir,String endStr) {
		List<File> list = new ArrayList<File>();
		File file = new File(ResourceMap.class.getClassLoader()
				.getResource(dir).getPath());
		if (file.isDirectory()) {
			for (File f : file.listFiles()) {
				if (f.getName().endsWith(endStr)) {
					list.add(f);
				}
			}
		}
		return list;
	}
	
	private static Properties getProperties(String filename){
		File file = new File(ResourceMap.class.getClassLoader().getResource(filename).getPath());
		return getProperties(file);
	}
	
	private static Properties getProperties(File file){
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			log.error("properties file not found",e);
		} catch (IOException e) {
			log.error("properties file IO exception",e);
		}
		return properties;
	}
	
	/**
	 * 获取资源文件值
	 * @param key
	 * @return
	 */
	public static String get(String key){
		return resourceMap.get(key);
	}
	/**
	 * 获取当前环境
	 * @return
	 */
	public static String getEvnDir() {
		return evnDir;
	}
	
	public static void flush(){
		resourceMap.clear();
		resourceMap = init();
	}
	
}
