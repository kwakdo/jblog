package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;

	public List<CategoryVo> findAll(String id) {
		return categoryRepository.findAll(id);
	}


	public void insert(CategoryVo categoryvo) {
		categoryRepository.insert(categoryvo);
	}


	public void delete(Long no) {
		categoryRepository.delete(no);
	}


	public CategoryVo getCategory(String id) {
		return categoryRepository.getCategory(id);
	}

		
}
