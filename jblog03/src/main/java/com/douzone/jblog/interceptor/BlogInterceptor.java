package com.douzone.jblog.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.vo.BlogVo;

public class BlogInterceptor implements HandlerInterceptor {
	
	@Autowired
	private BlogService blogService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		BlogVo sc = (BlogVo)request.getServletContext().getAttribute("blog");
	
		if(sc ==  null) {
			sc = blogService.getBlog();
			request.getServletContext().setAttribute("blog", sc);
			System.out.println(sc);
		}
		
		return true;
	}
	
	
	
	/*
	 * 모든 URL 다 받기
	 * 
	 * assets , user/login
	 * 
	 * prehandler(request)
	 * 
	 * sc = (request.getServletContext())
	 * 
	 * sc.getAttribute("site")
	 * 
	 * 
	 * 
	 */
}
