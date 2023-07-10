package com.projeto.integrador.Repository;


import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projeto.integrador.Entity.Post;

@Repository
public interface PostRepository extends CrudRepository<Post,Integer> {
    Post save(Post post);
    ArrayList<Post> findAll();
}