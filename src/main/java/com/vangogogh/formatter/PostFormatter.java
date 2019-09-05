package com.vangogogh.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

import com.vangogogh.model.Post;
import com.vangogogh.service.PostService;

public class PostFormatter implements Formatter<Post> {

	@Autowired
	private PostService postService;

	public PostFormatter() {
		super();
	}

	@Override
	public Post parse(final String text, final Locale locale) throws ParseException {
		final Long postId = Long.valueOf(text);
		return postService.findById(postId);
	}

	@Override
	public String print(final Post post, final Locale locale) {
		return (post != null ? post.getId().toString() : "");
	}

}
