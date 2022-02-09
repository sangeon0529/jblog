package com.poscoict.jblog.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poscoict.jblog.service.BlogService;
import com.poscoict.jblog.service.CategoryService;
import com.poscoict.jblog.service.FileUploadService;
import com.poscoict.jblog.service.PostService;
import com.poscoict.jblog.vo.BlogVo;
import com.poscoict.jblog.vo.CategoryVo;
import com.poscoict.jblog.vo.PostVo;
import com.poscoict.jblog.vo.UserVo;

@Controller
@RequestMapping("/jblog/{id:(?!assets).*}")
public class BlogController {

	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private PostService postService;
	@Autowired
	private FileUploadService fileUploadService;

	@RequestMapping({ "", "/{pathNo1}", "/{pathNo1}/{pathNo2}" })
	public String main(@PathVariable("id") String id, @PathVariable("pathNo1") Optional<Long> pathNo1,
			@PathVariable("pathNo2") Optional<Long> pathNo2, Model model) {
		BlogVo blogVo = blogService.getContents(id);
		List<CategoryVo> list = categoryService.getContents(id);
		Long categoryNo = 0L;//list.get(0).getNo();
		Long postNo = 0L;

		if (pathNo2.isPresent()) {
			categoryNo = pathNo1.get();
			postNo = pathNo2.get();
		} else if (pathNo1.isPresent()) {
			categoryNo = pathNo1.get();
		}
		List<PostVo> postlist = postService.getContents(categoryNo);
		PostVo postvo = postService.getContent(postNo);
		model.addAttribute("postvo", postvo);
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("list", list);
		model.addAttribute("postlist", postlist);
		model.addAttribute("id", id);
		return "blog/blog-main";
	}

	@RequestMapping("/admin/basic")
	public String basic(@PathVariable("id") String id, HttpServletRequest request, Model model) {
		String logId = null;
		HttpSession session = request.getSession();
		UserVo vo = (UserVo) session.getAttribute("user");
		try {
			logId = vo.getId();
		} catch (Exception e) {

		}

		if ((!id.equals(logId)) || logId == null) {
			return "redirect:/jblog/" + id;
		}
		BlogVo BVo = blogService.getContent(id);
		model.addAttribute("blogVo", BVo);
		return "blog/blog-admin-basic";
	}

	@RequestMapping(value = "/admin/basic", method = RequestMethod.POST)
	public String basic(@PathVariable("id") String id, HttpServletRequest request, BlogVo vo,
			@RequestParam(value = "logo-file") MultipartFile multipartFile) {
		System.out.println(vo);
		String logId = null;
		HttpSession session = request.getSession();
		try {
			logId = ((UserVo) session.getAttribute("user")).getId();
		} catch (Exception e) {

		}
		if ((!id.equals(logId)) || logId == null) {
			return "redirect:/jblog/" + id;
		}
		String url = fileUploadService.restore(multipartFile);
		if (url == null) {
			url = blogService.getContents(id).getLogo();
		}
		vo.setLogo(url);
		blogService.update(vo);
		return "blog/blog-admin-basic";
	}

	@RequestMapping("/admin/category")
	public String category(@PathVariable("id") String id, HttpServletRequest request, Model model) {
		String logId = null;
		HttpSession session = request.getSession();
		try {
			logId = ((UserVo) session.getAttribute("user")).getId();
		} catch (Exception e) {

		}
		System.out.println(id);
		System.out.println(logId);
		if ((!id.equals(logId)) || logId == null) {
			return "redirect:/jblog/" + id;
		}
		List<CategoryVo> list = categoryService.getContents(id);
		model.addAttribute("list", list);
		return "blog/blog-admin-category";
	}

	@RequestMapping(value = "/admin/category", method = RequestMethod.POST)
	public String category(@PathVariable("id") String id, HttpServletRequest request, CategoryVo vo) {
		String logId = null;
		HttpSession session = request.getSession();
		try {
			logId = ((UserVo) session.getAttribute("user")).getId();
		} catch (Exception e) {

		}
		if ((!id.equals(logId)) || logId == null) {
			return "redirect:/jblog/" + id;
		}
		categoryService.insert(vo, id);
		return "redirect:/jblog/" + id + "/admin/category";
	}

	@RequestMapping("/admin/write")
	public String write(@PathVariable("id") String id, HttpServletRequest request, Model model) {
		String logId = null;
		HttpSession session = request.getSession();
		try {
			logId = ((UserVo) session.getAttribute("user")).getId();
		} catch (Exception e) {

		}
		if ((!id.equals(logId)) || logId == null) {
			return "redirect:/jblog/" + id;
		}
		List<CategoryVo> list = categoryService.getContents(id);
		model.addAttribute("list", list);

		return "blog/blog-admin-write";

	}

	@RequestMapping(value = "/admin/write", method = RequestMethod.POST)
	public String write(@PathVariable("id") String id, HttpServletRequest request, PostVo vo) {
		String logId = null;
		HttpSession session = request.getSession();
		try {
			logId = ((UserVo) session.getAttribute("user")).getId();
		} catch (Exception e) {

		}
		if ((!id.equals(logId)) || logId == null) {
			return "redirect:/jblog/" + id;
		}
		System.out.println(vo);
		postService.write(vo);
		return "redirect:/jblog/" + id;

	}

	@RequestMapping("/admin/delete/{no}")
	public String delete(@PathVariable("id") String id, @PathVariable("no") Long no, HttpServletRequest request,
			Model model) {
		String logId = null;
		HttpSession session = request.getSession();
		try {
			logId = ((UserVo) session.getAttribute("user")).getId();
		} catch (Exception e) {

		}
		if ((!id.equals(logId)) || logId == null) {
			return "redirect:/jblog/" + id;
		}
		if(categoryService.getContents(id).size()<2) {
			return "redirect:/jblog/" + id;
		}
		categoryService.deleteCategory(no);

		return "redirect:/jblog/" + id + "/admin/category";

	}
}
