package com.projeto.integrador.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.integrador.Entity.Comments;
import com.projeto.integrador.Service.CommentService;



@CrossOrigin
@RestController
@RequestMapping("/comments")
public class CommentController {
	@Autowired
	CommentService commentService;

	@PostMapping
	public Comments submitComment(@RequestBody Comments comments) {
		return commentService.save(comments);
	}

	@GetMapping("/{postId}")
	public List<Comments> getCommentsForPost(@PathVariable("postId") String postId) {
		return commentService.getAllCommentsForDb(postId);
	}
}