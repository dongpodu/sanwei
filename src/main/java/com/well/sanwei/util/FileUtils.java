package com.well.sanwei.util;

import org.apache.commons.lang3.StringUtils;

import com.well.sanwei.enu.FileType;

public class FileUtils {
	/**
	 * 阿里云oos域名
	 */
	private static String FILEURL_SNIPPET = "oss-cn-hangzhou.aliyuncs.com/";
	private static String RESOURCE_DIR = "/"+"RESOURCE";

	/**
	 * 列举filetype枚举
	 * 
	 * @return
	 */
	public static String listEnumFileType() {
		StringBuffer buffer = new StringBuffer();
		FileType[] types = FileType.values();
		for (int i = 0; i < types.length; i++) {
			buffer.append(types[i].name());
			if (i < types.length - 1) {
				buffer.append(",");
			}
		}
		return buffer.toString();
	}

	/**
	 * 检查文件类型是否符合doc,ppt,pdf,xls;类型
	 * 
	 * @param fileType
	 * @return
	 */
	public static boolean checkFileType(String fileType) {
		if (StringUtils.isBlank(fileType)) {
			return false;
		}

		for (FileType type : FileType.values()) {
			if (type.name().equals(fileType)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 根据文件类型判断是否为office文档类型
	 * 
	 * @param fileType
	 * @return
	 */
	public static boolean checkOffice(String fileType) {
		if (StringUtils.isBlank(fileType)) {
			return false;
		}

		for (FileType.OfficeType type : FileType.OfficeType.values()) {
			if (type.name().equals(fileType)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 检查文件上是否为图片
	 * 
	 * @param fileType
	 * @return
	 */
	public static boolean checkImage(String fileType) {
		if (StringUtils.isBlank(fileType)) {
			return false;
		}

		for (FileType.ImageType type : FileType.ImageType.values()) {
			if (type.name().equals(fileType)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 检查文件上是否为视频
	 * 
	 * @param fileType
	 * @return
	 */
	public static boolean checkVideo(String fileType) {
		if (StringUtils.isBlank(fileType)) {
			return false;
		}

		for (FileType.VideoType type : FileType.VideoType.values()) {
			if (type.name().equals(fileType)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取文件的url
	 * 
	 * @param bucketName
	 * @param key
	 * @return
	 */
	public static String url(String bucketName, String key) {
		return bucketName + "," + FILEURL_SNIPPET + key;
	}

	/**
	 * 获取资源路径
	 * 
	 * @return
	 */
	public static String absoluteRresourcePath() {
		String path = WebPathUtil.getWebAppPath() +RESOURCE_DIR;
		return path;
	}

	/**
	 * 特定文件类型的文件夹的绝对路径
	 * 
	 * @param fileType
	 * @return
	 */
	public static String absoluteFileTypeDir(FileType fileType) {
		if (fileType == null) {
			return "";
		}
		return WebPathUtil.getWebAppPath() + RESOURCE_DIR
				+ "/" + fileType.name();
	}

	/**
	 * 特定文件类型的文件夹的相对路径
	 * 
	 * @param fileType
	 * @return
	 */
	public static String relativeFileTypeDir(FileType fileType) {
		if (fileType == null) {
			return "";
		}
		return RESOURCE_DIR + "/" + fileType.name();
	}

	/**
	 * 文件相对路径
	 * 
	 * @param fileType
	 *            文件类型
	 * @param fileName
	 *            文件名（无后缀）
	 * @return
	 */
	public static String relativeFilePath(FileType fileType, String fileName) {
		if (fileType == null || StringUtils.isBlank(fileName)) {
			return "";
		}
		return relativeFileTypeDir(fileType) + "/" + fileName + "."
				+ fileType.name();
	}

	/**
	 * 特定文件类型的文件夹的绝对路径
	 * 
	 * @param fileType
	 * @return
	 */
	public static String absoluteFileTypeDir(String fileType) {
		if (!checkFileType(fileType)) {
			return "";
		}
		return WebPathUtil.getWebAppPath() + RESOURCE_DIR
				+ "/" + fileType;
	}

	/**
	 * 获得文件枚举类型
	 * 
	 * @param fileType
	 * @return
	 */
	public static FileType getFileType(String fileType) {
		if (StringUtils.isBlank(fileType)) {
			return null;
		}
		for (FileType type : FileType.values()) {
			if (type.name().equals(fileType)) {
				return type;
			}
		}
		return null;
	}
	
	/**
	 * 获取文件的后缀
	 * @param fileName
	 * @return
	 */
	public static String suffix(String fileName){
		if(StringUtils.isNotBlank(fileName)){
			int index = fileName.lastIndexOf(".");
			String suffix = fileName.substring(index+1);
			return suffix;
		}
		return "";
	}

	public static void main(String[] args) {
		// System.out.println(checkFileType("doc"));
	}
}
