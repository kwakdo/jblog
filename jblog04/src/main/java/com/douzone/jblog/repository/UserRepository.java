package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.UserVo;

@Repository
public class UserRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public void insert(UserVo vo) {
		 sqlSession.insert("user.insert", vo);
	}

	public UserVo findByIdAndPassword(UserVo vo) {
		return sqlSession.selectOne("user.finByIdAndPassword", vo);
	}

	public UserVo findById(String id) {
		return sqlSession.selectOne("user.findById", id);
	}

	public boolean update(UserVo vo) {
		return sqlSession.update("user.update", vo) == 1;
	}


}