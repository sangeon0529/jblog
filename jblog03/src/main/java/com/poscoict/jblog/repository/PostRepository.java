package com.poscoict.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscoict.jblog.vo.PostVo;
@Repository
public class PostRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public List<PostVo> findByID(Long i) {
		// TODO Auto-generated method stub
		return sqlSession.selectList("post.select", i);
	}

	public boolean write(PostVo vo) {
	
		return sqlSession.insert("post.write", vo)==1;
	}

	public PostVo findByNo(Long postNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("post.find",postNo);
	}

}
