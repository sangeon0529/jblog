package com.poscoict.jblog.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poscoict.jblog.service.BlogService;
import com.poscoict.jblog.service.CategoryService;
import com.poscoict.jblog.service.PostService;
import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.PostVo;
import com.poscoict.jblog.vo.UserVo;


@Controller
@RequestMapping("/jblog")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PostService postService;
	
	@RequestMapping("/{id}")
	public String main(@PathVariable("id") String id, Model model) {
		BlogVo blogVo = blogService.getContents(id);
		List<CategoryVo> list = categoryService.getContents(id);
		List<PostVo> postlist = postService.getContents(list.get(0).getNo());
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("list",list);
		model.addAttribute("postlist",postlist);
		model.addAttribute("id",id);
		return "blog/blog-main";
	}
	
	@RequestMapping("/{id}/admin/basic")
	public String basic(@PathVariable("id") String id,HttpServletRequest request, Model model){
		String logId = null;
		HttpSession session = request.getSession();
		UserVo vo = (UserVo) session.getAttribute("user");
		logId = vo.getId();
		if ((!id.equals(logId)) || logId == null) {
			return "redirect:/jblog/"+id;
		}
		BlogVo BVo = blogService.getContent(id);
		model.addAttribute("blogVo", BVo);
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping(value = "/{id}/admin/basic", method = RequestMethod.POST)
	public String basic(@PathVariable("id") String id,HttpServletRequest request, BlogVo vo){
		String logId = null;
		HttpSession session = request.getSession();
		logId = ((UserVo) session.getAttribute("user")).getId();
		System.out.println(id);
		System.out.println(logId);
		if ((!id.equals(logId)) || logId == null) {
			return "redirect:/jblog/"+id;
		}
		
		blogService.update(vo);
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping("/{id}/admin/category")
	public String category(@PathVariable("id") String id,HttpServletRequest request){
		String logId = null;
		HttpSession session = request.getSession();
		logId = ((UserVo) session.getAttribute("user")).getId();
		System.out.println(id);
		System.out.println(logId);
		if ((!id.equals(logId)) || logId == null) {
			return "redirect:/jblog/"+id;
		}
		return "blog/blog-admin-category";
		
	}
	
	@RequestMapping("/{id}/admin/write")
	public String write(@PathVariable("id") String id,HttpServletRequest request){
		String logId = null;
		HttpSession session = request.getSession();
		logId = ((UserVo) session.getAttribute("user")).getId();
		System.out.println(id);
		System.out.println(logId);
		if ((!id.equals(logId)) || logId == null) {
			return "redirect:/jblog/"+id;
		}
		return "blog/blog-admin-write";
		
	}
}

	
