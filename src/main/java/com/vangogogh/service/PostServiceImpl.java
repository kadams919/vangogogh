package com.vangogogh.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vangogogh.model.Post;
import com.vangogogh.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Override
	public List<Post> findAll() {
		return postRepository.findAll();
	}

	@Override
	public List<Post> findLatest5() {
		List<Post> posts = findAll();
		return posts.stream().sorted((a, b) -> b.getCreatedDate().compareTo(a.getCreatedDate())).limit(5)
				.collect(Collectors.toList());
	}

	@Override
	public Post findById(Long id) {
		Optional<Post> post = postRepository.findById(id);
		return post.isPresent() ? post.get() : null;
	}

	@Override
	public Post createPost(Post post) {
		Post newPost = Post.builder().user(post.getUser()).body(post.getBody()).title(post.getTitle())
				.locationAddress(post.getLocationAddress()).build();
		return postRepository.save(newPost);
	}

	@Override
	public Post updatePost(Post post) {
		Optional<Post> originalPost = postRepository.findById(post.getId());
		if (originalPost.isPresent()) {
			Post updatedPost = originalPost.get();
			updatedPost.setBody(post.getBody());
			updatedPost.setTitle(post.getTitle());
			updatedPost.setUser(post.getUser());
			updatedPost.setLocationAddress(post.getLocationAddress());

			return postRepository.save(updatedPost);
		}

		return null;
	}

	@Override
	public void deletePost(Long id) {
		postRepository.deleteById(id);
	}

}
