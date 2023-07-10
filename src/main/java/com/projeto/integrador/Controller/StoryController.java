package com.projeto.integrador.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.integrador.Entity.Story;
import com.projeto.integrador.Service.StoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/story")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class StoryController {

	@Autowired
	StoryService storyService;

	@PostMapping("")
	public Story submitStory(@RequestBody Story story) {
		return storyService.submitData(story);
	}

	@GetMapping("")
	public ArrayList<Story> getAllStories() {
		return storyService.retrieveStory();
	}
}