package com.poscoict.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.BlogRepository;
import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.UserVo;

@Service
public class BlogService {

	@Autowired
	private BlogRepository blogRepository;
	
	public BlogVo getContents(String id) {
		BlogVo vo = blogRepository.findByID(id);
		return vo;
	}

	public void createBlog(UserVo vo) {
		blogRepository.create(vo);
		
	}

}
