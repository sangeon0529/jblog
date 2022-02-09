package com.poscoict.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscoict.jblog.repository.CategoryRepository;
import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.UserVo;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public void create(UserVo vo) {
		categoryRepository.create(vo);
		
	}

	public List<CategoryVo> getContents(String id) {
		return categoryRepository.findByID(id);
	}

	public boolean insert(CategoryVo vo, String id) {
		return categoryRepository.insert(vo, id);
		
	}

	public boolean deleteCategory(Long no) {
		return categoryRepository.delete(no);
	}
	
}
