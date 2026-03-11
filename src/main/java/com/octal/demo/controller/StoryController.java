package com.octal.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.octal.demo.dto.EpisodeDto;
import com.octal.demo.entity.Episode;
import com.octal.demo.entity.Story;
import com.octal.demo.service.CloudinaryService;
import com.octal.demo.service.StoryService;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/stories")
public class StoryController {

	@Autowired
	StoryService storyService;

	private final String THUMBNAIL_PATH = "D:/videos/thumbnails/";

	@Autowired
	private CloudinaryService cloudinaryService;

	@PostMapping("/create")
	public Story createStory(@RequestParam String title, @RequestParam String description,
			@RequestParam MultipartFile thumbnail) throws Exception {

		// Upload thumbnail to Cloudinary
		String thumbnailUrl = cloudinaryService.uploadImage(thumbnail);

		Story story = new Story();
		story.setTitle(title);
		story.setDescription(description);
		story.setThumbnail(thumbnailUrl);

		return storyService.saveStory(story);
	}

//	@PostMapping("/create")
//	public Story createStory(@RequestParam String title, @RequestParam String description,
//			@RequestParam MultipartFile thumbnail) throws Exception {
//
//		Files.createDirectories(Paths.get(THUMBNAIL_PATH));
//
//		String fileName = thumbnail.getOriginalFilename();
//
//		Path path = Paths.get(THUMBNAIL_PATH + fileName);
//
//		Files.copy(thumbnail.getInputStream(), path);
//
//		Story story = new Story();
//		story.setTitle(title);
//		story.setDescription(description);
//		story.setThumbnail("/thumbnails/" + fileName);
//
//		return storyService.saveStory(story);
//	}

	@GetMapping
	public List<Story> getStories() {
		return storyService.getAllStories();
	}

	@GetMapping("/{id}/episodes")
	public List<EpisodeDto> getEpisodes(@PathVariable Long id) {
		return storyService.getEpisodes(id);
	}
}
