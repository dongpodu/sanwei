package com.well.sanwei.enu;

/**
 * 上传的文件类型
 * @author duyisong
 *
 */
public enum FileType {
	/**
	 * 普通文件类型
	 */
	doc,ppt,pdf,xls,jpg,gif,png,bmp,swf,asx,asf,flv,mp4;
	
	/**
	 * 图片类型
	 * @author duyisong
	 *
	 */
	public enum ImageType {
		jpg,gif,png,bmp;
	}
	
	/**
	 * 视频文件类型
	 * @author duyisong
	 *
	 */
	public enum VideoType {
		mp4,flv;
	}
	
	/**
	 * office文档类型
	 * @author duyisong
	 *
	 */
	public enum OfficeType {
		doc,ppt,pdf,xls;
	}
}
