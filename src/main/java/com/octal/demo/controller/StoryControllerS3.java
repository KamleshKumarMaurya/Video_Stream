package com.octal.demo.controller;

import com.octal.demo.dto.EpisodeDto;
import com.octal.demo.entity.Story;
import com.octal.demo.service.S3Service;
import com.octal.demo.service.StoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

//@RestController
//@RequestMapping("/api/stories")
//@CrossOrigin
public class StoryControllerS3 {

//	@Autowired
//	private StoryService storyService;
//
//	@Autowired
//	private S3Service s3Service;

//	@Value("${cloudfront.url}")
//	private String CDN_URL;

//	@PostMapping("/create")
//	public Story createStory(@RequestParam String title, @RequestParam String description,
//			@RequestParam MultipartFile thumbnail) throws Exception {
//
//		String key = s3Service.uploadThumbnail(thumbnail);
//
//		String thumbnailUrl = CDN_URL + "/" + key;
//
//		Story story = new Story();
//		story.setTitle(title);
//		story.setDescription(description);
//		story.setThumbnail(thumbnailUrl);
//
//		return storyService.saveStory(story);
//	}
//
//	@GetMapping
//	public List<Story> getStories() {
//		return storyService.getAllStories();
//	}
//
//	@GetMapping("/{id}/episodes")
//	public List<EpisodeDto> getEpisodes(@PathVariable Long id) {
//		return storyService.getEpisodes(id);
//	}
}