package com.projeto.integrador.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.integrador.Entity.Post;
import com.projeto.integrador.Service.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class PostController {

	@Autowired
	PostService postService;

	@PostMapping("")
	private Post submitUserPost(@RequestBody Post post) {
		return postService.submitPostToDb(post);
	}

	@GetMapping("")
	private ArrayList<Post> getAllPost() {
		return postService.retrievePostFromDb();
	}
}