package com.projeto.integrador.Repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projeto.integrador.Entity.Story;


@Repository
public interface StoryRepository extends CrudRepository<Story,Integer> {
    Story save(Story story);
    ArrayList<Story> findAll();

}