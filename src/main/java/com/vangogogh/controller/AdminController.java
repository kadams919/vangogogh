package com.vangogogh.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.vangogogh.model.Post;
import com.vangogogh.service.PostService;

@Controller
public class AdminController {

	@Autowired
	private PostService postService;

	@ModelAttribute("allPosts")
	public List<Post> populateAllPosts() {
		return postService.findAll();
	}

	@GetMapping("/admin")
	public String getPosts(Model model) {
		return "admin/home";
	}

	@GetMapping("/admin/post")
	public String newPost(Model model) {
		Post post = new Post();
		model.addAttribute("post", post);
		model.addAttribute("message", "Write about your adventure");
		return "admin/post";
	}

	@PostMapping("/admin/post")
	public String createPost(@Valid Post post, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "admin/post";
		} else {
			postService.createPost(post);
			return "redirect:/home";
		}
	}

	@GetMapping("/admin/posts")
	public String editPosts(Model model) {
		return "admin/posts";
	}

	@GetMapping("/admin/post/{id}")
	public String getEditPost(@PathVariable Long id, Model model) {
		Post post = postService.findById(id);
		model.addAttribute("post", post);
		model.addAttribute("message", "Edit your adventure");

		return "admin/update-post";
	}

	@PostMapping("/admin/update-post/{id}")
	public String updatePost(@Valid Post post, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "admin/post";
		} else {
			postService.updatePost(post);
			return "redirect:/";
		}
	}

	@GetMapping("/admin/delete-post/{id}")
	public String deletePost(@PathVariable Long id) {
		postService.deletePost(id);

		return "redirect:/admin/posts";
	}

}
