package com.well.sanwei.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.well.sanwei.dao.ResourceDao;
import com.well.sanwei.model.Resource;

@Controller
@RequestMapping("/*")
public class IndexController extends BaseController{
	@Autowired
	private ResourceDao resourceDao;
	
	/**
	 * 首页
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(){
		Resource resource = new Resource();
		resource.setResourceType("1");
		List<Resource> docList = resourceDao.queryList(resource);  //课件
		resource.setResourceType("2");
		List<Resource> videoList = resourceDao.queryList(resource); //视频
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index");
		mv.addObject("docList", docList);
		mv.addObject("videoList", videoList);
		return mv;
	}
	
	/**
	 * 搜索
	 * @param courseName
	 * @return
	 */
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public ModelAndView search(String key){
		Resource resource = new Resource();
		resource.setCourseName(key);
		resource.setCreatName(key);
		List<Resource> resourceList = resourceDao.queryByKey(resource);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("searchResult");
		mv.addObject("resourceList", resourceList);
		return mv;
	}
	
}
