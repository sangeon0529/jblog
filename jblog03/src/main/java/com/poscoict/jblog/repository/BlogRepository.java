package com.poscoict.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.UserVo;

@Repository
public class BlogRepository {
	@Autowired
	private SqlSession sqlSession;

	public void create(UserVo vo) {
		sqlSession.insert("blog.create", vo);
		
	}

	public BlogVo findByID(String id) {
		BlogVo vo = sqlSession.selectOne("blog.select",id);
		return vo;
	}
}
