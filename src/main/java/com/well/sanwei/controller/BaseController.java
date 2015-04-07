package com.well.sanwei.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.well.sanwei.model.Constants;
import com.well.sanwei.model.User;

@Controller
public class BaseController {
	protected static final String RETURN_OK="ok";
	
	@InitBinder
	// 此处的参数也可以是ServletRequestDataBinder类型
	public void initBinder(WebDataBinder binder) throws Exception {
		// 注册自定义的属性编辑器
		// 1、日期
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor dateEditor = new CustomDateEditor(df, true);
		// 表示如果命令对象有Date类型的属性，将使用该属性编辑器进行类型转换
		binder.registerCustomEditor(Date.class, dateEditor);
		// 自定义的电话号码编辑器(和【4.16.1、数据类型转换】一样)
		// binder.registerCustomEditor(PhoneNumberModel.class, new
		// PhoneNumberEditor());
	}
	
	public User getLoginUser(){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.
				getRequestAttributes()).getRequest();
		HttpSession session = request.getSession(false);
		if(session!=null){
			User user = (User)session.getAttribute(Constants.SESSION_USER);
			return user;
		}
		return null;
	}
}
