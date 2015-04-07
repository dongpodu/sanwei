package com.well.sanwei.inteceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.well.sanwei.dao.UserDao;
import com.well.sanwei.model.Constants;
import com.well.sanwei.model.User;

/**
 * 登录拦截器
 */
@Component
public class LoginInteceptor extends HandlerInterceptorAdapter {
	@Autowired
	UserDao userDao;
	
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		
		Cookie[] cookies = request.getCookies();
		if(cookies==null){
			return false;
		}
		
		//验证cookie
		for(Cookie cookie:cookies){
			if(Constants.COOKIE_USER.equals(cookie.getName())){
				int userId = Integer.parseInt(cookie.getValue());
				HttpSession session = request.getSession(false);
				//此次登录人和上一次是同一个人
				if(session!=null && session.getAttribute(Constants.SESSION_USER)!=null
						&&((User)session.getAttribute(Constants.SESSION_USER)).getId()==userId ){
					return true;
				}
			}
		}
        return false;
	}
}
