package com.vangogogh.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vangogogh.model.Post;
import com.vangogogh.service.PostService;

@Controller
public class HomeController {

	@Autowired
	PostService postService;

	@ModelAttribute("allLocations")
	public List<String> getAllLocations() {
		List<String> allLocations = new ArrayList<>();
		List<Post> allPosts = postService.findAll();
		for (Post post : allPosts) {
			allLocations.add(post.getLocationAddress());
		}
		return allLocations;
	}

	@RequestMapping(value = { "/", "/home" })
	public String home(Model model) {
		List<Post> latest5Posts = postService.findLatest5();
		model.addAttribute("latest5Posts", latest5Posts);

		return "/home";
	}

	@RequestMapping(value = "/home2")
	public String home2() {
		return "/home2";
	}

//	@GetMapping("/home")
//	public String home() {
//		return "home";
//	}
}
