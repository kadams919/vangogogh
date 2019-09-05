package com.vangogogh.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vangogogh.model.Post;
import com.vangogogh.service.PostService;

@Controller
public class HomeController {

	@Autowired
	PostService postService;

	@RequestMapping(value = { "/", "/home" })
	public String home(Model model) {
		List<Post> latest5Posts = postService.findLatest5();
		model.addAttribute("latest5Posts", latest5Posts);

		return "/home";
	}

//	@GetMapping("/home")
//	public String home() {
//		return "home";
//	}
}
