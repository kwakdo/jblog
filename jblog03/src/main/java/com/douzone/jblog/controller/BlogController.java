package com.douzone.jblog.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.jblog.security.Auth;
import com.douzone.jblog.security.AuthUser;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.FileUploadService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	
	@Autowired
	BlogService blogService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	PostService postService;

	@Autowired
	FileUploadService fileUploadService;


	@RequestMapping({"", "/{pathNo1}", "/{pathNo1}/{pathNo2}"})
	public String index(
		@PathVariable("id") String id,
		@PathVariable("pathNo1") Optional<Long> pathNo1,
		@PathVariable("pathNo2") Optional<Long> pathNo2,
		Model model) {
		
		Long categoryNo = 0L;
		Long postNo = 0L;
		
		if(pathNo2.isPresent()) {
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();
		} else if(pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
		}
		return "blog/main";
		//return "BlogController.index(" + id + ", " + categoryNo + ", " + postNo + ")";
	}
	
	@Auth
	@RequestMapping("/admin/basic")
	public String adminBasic(@PathVariable("id") String id, @AuthUser UserVo authUser) {
		if(!authUser.getId().equals(id)) {
			return "redirect:/";
		}
		
		return "blog/admin/basic";
	}
	
	@Auth
	@RequestMapping("admin/category")
	public String adminCategory(
			@AuthUser UserVo authUser, 
			Model model) {
		
		model.addAttribute("blogVo", blogService.getBlog(authUser.getId()));
		List<CategoryVo> list = categoryService.getList(authUser.getId());

		model.addAttribute("categoryList", list);
		return "blog/admin/category";
	}
	
	@Auth
	@RequestMapping(value = "/admin/write", method = RequestMethod.GET)
	public String adminWrite(@AuthUser UserVo authUser, Model model) {
		model.addAttribute("list", blogService.findCategoryNoAndName(authUser.getId()));
		return "/blog/admin/write";
	}
	
	@Auth
	@RequestMapping(value = "/admin/write", method = RequestMethod.POST)
	public String adminWrite(@AuthUser UserVo authUser, PostVo postVo) {
		blogService.write(postVo);
		return "redirect:/" + authUser.getId() + "/admin/write";
	}
	
}