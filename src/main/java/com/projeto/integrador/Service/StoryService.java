package com.projeto.integrador.Service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.integrador.Entity.Story;
import com.projeto.integrador.Repository.StoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoryService {

	@Autowired StoryRepository storyRepository;
	@Autowired UserService userService;
    public Story submitData(Story story){
        return storyRepository.save(story);
    }

    public ArrayList<Story> retrieveStory(){
        ArrayList<Story> storyArrayList = storyRepository.findAll();
        for (Story storyItem:storyArrayList){
            Story storyValue = storyItem;
            String userIdfromStory = storyValue.getUserId();
            storyValue.setUserName(userService.displayUserData(userIdfromStory).getUserName());
        }

        /*!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
        return storyArrayList;
    }
}















