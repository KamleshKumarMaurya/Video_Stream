package com.octal.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.octal.demo.dto.EpisodeDto;
import com.octal.demo.entity.Episode;
import com.octal.demo.entity.Story;
import com.octal.demo.repo.EpisodeRepository;
import com.octal.demo.repo.StoryRepository;

@Service
public class StoryService {

	@Autowired
	StoryRepository storyRepository;

	@Autowired
	EpisodeRepository episodeRepository;

	public Story saveStory(Story story) {
		return storyRepository.save(story);
	}

	public List<Story> getAllStories() {
		return storyRepository.findAll();
	}

	public List<EpisodeDto> getEpisodes(Long storyId) {
		return episodeRepository.findEpisodesByStoryId(storyId);
	}
}
