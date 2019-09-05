package com.vangogogh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.vangogogh.model.Post;
import com.vangogogh.service.PostService;

@Controller
public class PostController {

	@Autowired
	private PostService postService;

	@ModelAttribute("allPosts")
	public List<Post> populateAllPosts() {
		return postService.findAll();
	}

	@GetMapping("/posts")
	public String allPosts() {
		return "/posts";
	}

	@GetMapping("/post/{id}")
	public String getAllPosts(@PathVariable Long id, Model model) {
		Post post = postService.findById(id);
		model.addAttribute("post", post);

		return "/post";
	}

}
