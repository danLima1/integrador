package com.projeto.integrador.Service;

import java.util.ArrayList;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.integrador.Entity.Post;
import com.projeto.integrador.Repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

	@Autowired
     UserService userService;
	@Autowired
    PostRepository postRepository;
    public Post submitPostToDb(Post post){
        return postRepository.save(post);
    }
    public ArrayList<Post> retrievePostFromDb(){
        ArrayList<Post> postArrayList = postRepository.findAll();
        for(Post postItem: postArrayList){
            Post postValue = postItem;
            String userIdfromPost = postValue.getUserId();
            postValue.setUserName(userService.displayUserData(userIdfromPost).getUserName());
        }

        Collections.sort(postArrayList,(a,b)->b.getId()-a.getId());
        return postArrayList;
    }
}