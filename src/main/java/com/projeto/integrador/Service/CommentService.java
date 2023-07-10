package com.projeto.integrador.Service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.integrador.Entity.Comments;
import com.projeto.integrador.Repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

	@Autowired
    CommentRepository commentRepository;
	@Autowired
     UserService userService;
    public Comments save(Comments comment){
        return  commentRepository.save(comment);
    }
    public List<Comments> getAllCommentsForDb(String postId){
        List<Comments> commentArrayList = commentRepository.findAllByPostId(postId);
        for (Comments commentItem:commentArrayList){
            Comments commentValue = commentItem;
            String userIdfromComment = commentValue.getUserId();
            commentValue.setUserName(userService.displayUserData(userIdfromComment).getUserName());
        }

        /*for (int i = 0; i<commentArrayList.size();i++){
            Comments commentValue = commentArrayList.get(i);
            commentValue.setUserName(userService.displayUserData(commentValue.getUserId()).getUserName());
        }*/
        return commentArrayList;
    }
}