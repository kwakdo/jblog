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

	public void insert(UserVo vo) {
		sqlSession.insert("category.insert", vo);
	}

	public List<CategoryVo> findByList(String blogId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("blogId", blogId);
		return sqlSession.selectList("category.findByList", map);

	}

	public CategoryVo findByNo(String blogId, Long no) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("no", no);
		map.put("blogId", blogId);
		return sqlSession.selectOne("category.findByNo", map);

	}
	
}
