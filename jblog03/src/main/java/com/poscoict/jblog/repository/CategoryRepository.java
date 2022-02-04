package com.poscoict.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.UserVo;

@Repository
public class CategoryRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public void create(UserVo vo) {
		sqlSession.insert("category.create", vo);
		
	}

	public List<CategoryVo> findByID(String id) {
		return sqlSession.selectList("category.select",id);
	}

}
