package com.poscoict.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.UserRepository;
import com.poscoict.jblog.vo.UserVo;


@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	public UserVo login(String id, String password) {
		UserVo vo = userRepository.findByIDAndPassword(id, password);
		return vo;
	}
	
	public void join(UserVo vo) {
		userRepository.insert(vo);

		// TODO Auto-generated method stub
		
	}

}
