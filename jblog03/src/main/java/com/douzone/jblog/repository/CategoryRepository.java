package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.UserVo;

@Repository
public class CategoryRepository {

	@Autowired
	private SqlSession sqlSession;

	// 회원가입 시
	public void insertJoin(UserVo vo) {
		sqlSession.insert("category.insertJoin", vo);
	}

	// 카테고리 추가 시
	public void insert(CategoryVo categoryVo) {
		sqlSession.insert("category.insert", categoryVo);
	}
	
	public List<CategoryVo> findAll(String id) {
		return sqlSession.selectList("category.findAll", id);
	}

	public void delete(Long no) {
		sqlSession.delete("category.delete", no);
	}

	public CategoryVo getCategory(String id) {
		return sqlSession.selectOne("category.getCategory", id);
	}

	
	
}
