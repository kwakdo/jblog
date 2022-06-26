package com.douzone.jblog.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.security.Auth;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.FileUploadService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;
 
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
	
	@Autowired
	private ServletContext servletContext;


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
		List<CategoryVo> categorylist = categoryService.findAll(id);
		model.addAttribute("categorylist", categorylist);
		System.out.println("categoryList: " + categorylist);
		List<PostVo> postlist = postService.findAll(id, categoryNo);
		model.addAttribute("postlist", postlist);
		System.out.println("postlist: " + postlist);
		if(categoryNo == 0L)
			categoryNo = categoryService.getCategory(id).getNo();
		PostVo postvo = postService.findByNo(categoryNo, postNo);
		model.addAttribute("postvo", postvo);
		System.out.println("postvo: " + postvo);
		BlogVo blogvo = blogService.getBlog(id);
		servletContext.setAttribute("blogvo", blogvo);
		
		
		model.addAttribute("id", id);
		
		return "blog/main";
	}
	
	@Auth
	@RequestMapping(value = "/admin/basic", method = RequestMethod.GET)
	public String adminBasic(@PathVariable("id") String id) {
		return "/blog/admin/basic";
	}
	
	
	@Auth
	@RequestMapping(value = "/admin/basic", method = RequestMethod.POST)
	public String update(
				@ModelAttribute BlogVo blogvo, 
				@RequestParam(value = "file")MultipartFile multipartFile,
				Model model) {
		
		String url = fileUploadService.restore(multipartFile);
		blogvo.setLogo(url);
		
		blogService.updateBlog(blogvo);
		model.addAttribute("blogvo", blogvo);
		return "redirect:/" + blogvo.getId();
	}
	
	@Auth
	@RequestMapping(value = "/admin/category", method = RequestMethod.GET)
	public String category(
			@PathVariable("id") String id, Model model) {
		List<CategoryVo> categorylist = categoryService.findAll(id);
		model.addAttribute("categorylist", categorylist);
		return "blog/admin/category";
	}
	
	@Auth
	@RequestMapping(value = "/admin/category", method = RequestMethod.POST)
	public String category(
			@PathVariable("id") String id, 
			@ModelAttribute CategoryVo categoryVo) {
		categoryVo.setBlogId(id);
		categoryService.insert(categoryVo);
		return "redirect:/" + id + "/admin/category";
	}
	
	@Auth
	@RequestMapping(value = "/admin/category/delete/{no}", method = RequestMethod.GET)
	public String delete(
			@PathVariable("id") String id, 
			@PathVariable("no") Long no) {
		categoryService.delete(no);
		return "redirect:/" + id + "/admin/category";
	}
	
	@Auth
	@RequestMapping(value = "/admin/write", method = RequestMethod.GET)
	public String write(
			@PathVariable("id") String id, Model model) {
		List<CategoryVo> categorylist = categoryService.findAll(id);
		model.addAttribute("categorylist", categorylist);
			return "blog/admin/write";
		}
	
	@Auth
	@RequestMapping(value = "/admin/write", method = RequestMethod.POST)
	public String write(
			@PathVariable("id") String id, 
			@RequestParam("category") String category,
			@ModelAttribute PostVo postvo) {
		postService.write(id, category, postvo);
		return "redirect:/" + id;
	}
	
}