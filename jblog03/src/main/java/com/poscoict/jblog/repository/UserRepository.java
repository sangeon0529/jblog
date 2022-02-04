package com.poscoict.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.UserVo;
@Repository
public class UserRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public UserVo findByIDAndPassword(String id, String password) {
		Map<String, String> map = new HashMap<>();
		map.put("i", id);
		map.put("p", password);
		UserVo a = sqlSession.selectOne("user.findByIDAndPassword", map);
		System.out.println("sql"+a);
		return a;
	}

	public void insert(UserVo vo) {
		sqlSession.insert("user.insert", vo);
		
	}

}
