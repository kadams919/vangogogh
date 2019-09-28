package com.vangogogh.service;

import java.util.List;

import com.vangogogh.model.Post;

public interface PostService {
	List<Post> findAll();

	List<Post> findLatest5();

	Post findById(Long id);

	Post createPost(Post post);

	Post updatePost(Post post);

	void deletePost(Long id);

}
