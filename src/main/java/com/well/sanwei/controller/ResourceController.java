package com.well.sanwei.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.aliyun.openservices.oss.OSSClient;
import com.aliyun.openservices.oss.model.OSSObject;
import com.well.sanwei.dao.ResourceDao;
import com.well.sanwei.dao.UserDao;
import com.well.sanwei.model.Resource;
import com.well.sanwei.model.User;
import com.well.sanwei.service.ResourceService;
import com.well.sanwei.util.FileUtils;

@Controller
@RequestMapping("/*")
public class ResourceController extends BaseController{
	//阿里云buket参数
	private String bucketName = "sanwei";
	private String keyId = "2lAfaf7Z5z22u6GS";
	private String keyScret = "QEcPL7BwW71XWjNlwcgGdvfZKyVQ15";
	private OSSClient client = new OSSClient(keyId,keyScret);
	private String fileName = "file";
	private static int MAX_SIZE=1024*1024*500; //文件最大为500M
	
	@Autowired
	private ResourceService resourceService;
	@Autowired
	private ResourceDao resourceDao;
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value="/toUpload")
	public String toUpload(){
		return "upload";
	}
	
	
	//上传文件，文件名为file，返回该文件的相对路径
	@RequestMapping(value="/upload",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,String> upload(HttpServletRequest request) throws Exception{
		// 获得文件
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;   
        MultipartFile file = multipartRequest.getFile(fileName);
        //判断文件大小
        if(file.getSize()>MAX_SIZE){
        	throw new IllegalArgumentException("上传的文件大小不能超过500M！");
        }
        //上传
        String suffix = FileUtils.suffix(file.getOriginalFilename());
        InputStream content = file.getInputStream();
        String path  = resourceService.upload(file.getInputStream(), FileUtils.getFileType(suffix));
        Map<String,String> map = new HashMap<String,String>();
        map.put("path", path);
		content.close();
		return map;
	}
	
	/**
	 * 文件下载
	 * @param key
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/download",method=RequestMethod.GET)
	@ResponseBody
	public void download(String key,HttpServletResponse response) throws IOException{
		if(StringUtils.isBlank(key)){
        	throw new IllegalArgumentException("所传的key不能为空！");
        }
		response.setContentType("application/x-shockwave-flash");
		response.addHeader("Content-Disposition", "attachment;filename="
				+new String(key.getBytes("utf-8"),"iso-8859-1"));
		OSSObject object = client.getObject(bucketName, key);
		if(object!=null){
			BufferedInputStream bis = new BufferedInputStream(object.getObjectContent());
			BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
			int bytesRead;
			byte[] buffer = new byte[1024];
			while(-1 != (bytesRead = bis.read(buffer, 0, buffer.length))){
				bos.write(buffer, 0, bytesRead);
			}
		}
	}
	
	@RequestMapping(value="/saveResource",method=RequestMethod.POST)
	public ModelAndView saveResource(Resource resource){
		User user = getLoginUser();
		resourceService.saveResource(resource, user);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/index");
		return mv;
	}
	
	/**
	 * office预览
	 * @param key
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/view",method=RequestMethod.GET)
	public ModelAndView view(String id){
		ModelAndView mv = new ModelAndView();
		if(StringUtils.isBlank(id)){
			mv.addObject("msg", "对不起，你要找的资源不存在!");
			mv.setViewName("redirect:/index");
		}
		Resource rs = resourceService.getResource(Integer.parseInt(id));
		if(FileUtils.checkVideo(rs.getFileType())){
			mv.setViewName("videoView");
		}
		else if(FileUtils.checkOffice(rs.getFileType())){
			String swfPath = resourceService.getSwfPath(rs.getResourceUrl());
			rs.setResourceUrl(swfPath);
			mv.setViewName("officeView");
		}
		else{
			mv.setViewName("imageView");
		}
		mv.addObject("relatedResource",resourceDao.queryByUserId(rs.getCreatorId()));
		mv.addObject("resource", rs);
		return mv;
	}
	
	/**
	 * 视频
	 * @return
	 */
	@RequestMapping(value="/video")
	public String toVideo(){
		return "video";
	}
	
}
