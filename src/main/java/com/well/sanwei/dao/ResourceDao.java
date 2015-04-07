package com.well.sanwei.dao;

import java.util.List;

import com.well.sanwei.model.Resource;

public interface ResourceDao {
	
	public Resource queryById(int id);
	
	public int insert(Resource resource);
	/**
	 * 根据非空字段
	 * primaryCategory、sencondCategory、thirdCategory、resourceType查找资源列表
	 * @param resource
	 * @return
	 */
	public List<Resource> queryList(Resource resource);
	
	/**
	 * 根据关键字查找匹配提供者姓名或课程名的资源
	 * @param resource
	 * @return
	 */
	public List<Resource> queryByKey(Resource resource);
	
	/**
	 * 根据userId查找资源
	 * @param userId
	 * @return
	 */
	public List<Resource> queryByUserId(int userId);
}
