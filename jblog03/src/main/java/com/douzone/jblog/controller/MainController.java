package com.douzone.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.jblog.security.AuthUser;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.UserVo;

@Controller
public class MainController {
	@Autowired
	private BlogService blogService;
	
	@RequestMapping("/")
	public String index(@AuthUser UserVo authUser, Model model) {
		BlogVo vo = blogService.getBlog();
		
		model.addAttribute("blog", vo);
		model.addAttribute("authUser", authUser);
		System.out.println("vo: "+ vo + "  //  " + "authUser: " + authUser);
		return "main/index";
	}
}