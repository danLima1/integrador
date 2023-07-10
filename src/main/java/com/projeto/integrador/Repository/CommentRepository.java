package com.projeto.integrador.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projeto.integrador.Entity.Comments;

@Repository
public interface CommentRepository extends CrudRepository<Comments,Integer> {
    Comments save(Comments comment);
    List<Comments> findAllByPostId(String postId);
}