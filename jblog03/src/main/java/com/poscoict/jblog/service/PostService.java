package com.poscoict.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.PostRepository;
import com.poscoict.jblog.vo.PostVo;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	public List<PostVo> getContents(Long i) {
		// TODO Auto-generated method stub
		return postRepository.findByID(i);
	}

	public boolean write(PostVo vo) {
		return postRepository.write(vo);
		// TODO Auto-generated method stub
		
	}

}
