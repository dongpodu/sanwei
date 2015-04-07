package com.well.sanwei.controller;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.well.sanwei.dao.UserDao;
import com.well.sanwei.model.Constants;
import com.well.sanwei.model.User;

@Controller
@RequestMapping("/*")
public class LoginController extends BaseController{
	@Autowired
	private UserDao userDao;
	
	/**
	 * 跳转到注册页面
	 * @return
	 */
	@RequestMapping("/register")
	public String register(){
		return "register";
	}
	
	/**
	 * 验证邮箱
	 * @param email
	 * @return
	 */
	@RequestMapping("/checkemail")
	@ResponseBody
	public boolean checkEmail(String email){
		User old = userDao.queryByEmail(email);
		if(old!=null){
			return false;
		}
		return true;
	}
	
	@RequestMapping("/checkemailexists")
	@ResponseBody
	public boolean checkEmailExsits(String email){
		User old = userDao.queryByEmail(email);
		if(old==null){
			return false;
		}
		return true;
	}
	
	@RequestMapping("/checkpwd")
	@ResponseBody
	public boolean checkpwd(User user){
		User old = userDao.queryByEmail(user.getEmail());
		if(old!=null && old.getPassword()!=null
				&& old.getPassword().equals(user.getPassword())){
			return true;
		}
		return false;
	}
	
	/**
	 * 注册
	 * @param request
	 * @param user
	 */
	@RequestMapping(value="/registered",method=RequestMethod.POST)
	public ModelAndView registered(HttpServletRequest request,User user){
		//保存
		userDao.insert(user);
		//设置session
		HttpSession session = request.getSession(true);
		session.setAttribute(Constants.SESSION_USER, user);
		//返回
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/index");
		mv.addObject("user", user);
		return mv;
	}
	
	/**
	 * 跳转到登录页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
	/**
	 * 登录
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/logined")
	public ModelAndView logined(HttpServletRequest request,User paramUser,HttpServletResponse response){
		User user = userDao.queryByEmail(paramUser.getEmail());
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/index");
		if(user!=null && user.getPassword().equals(paramUser.getPassword())){
			Cookie cookie = new Cookie(Constants.COOKIE_USER,
					String.valueOf(user.getId()));
			response.addCookie(cookie);
			HttpSession session = request.getSession(true);
			session.setAttribute(Constants.SESSION_USER, user);
			mv.addObject("user", user);
		}
		else{
			mv.addObject("msg", "账户名或密码错误，登录失败！");
		}
		return mv;
	}
	
	/**
	 * 登出
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/logout",method = RequestMethod.GET)
	protected String logout(HttpServletRequest request,Model model){
		Cookie[] cookies =  request.getCookies();
		HttpSession session  = request.getSession();
		for(Cookie cookie:cookies){
			if(cookie.getName().equals(Constants.COOKIE_USER)){
				session.removeAttribute(Constants.SESSION_USER);
				break;
			}
		}
		return "redirect:/index";
	}
}
