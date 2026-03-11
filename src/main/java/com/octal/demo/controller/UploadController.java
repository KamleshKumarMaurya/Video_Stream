package com.octal.demo.controller;

import java.nio.file.*;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.octal.demo.entity.Episode;
import com.octal.demo.entity.Story;
import com.octal.demo.repo.EpisodeRepository;
import com.octal.demo.repo.StoryRepository;
import com.octal.demo.service.CloudinaryService;
import com.octal.demo.service.VideoProcessingService;

@RestController
@RequestMapping("/api/upload")
public class UploadController {

	@Autowired
	private StoryRepository storyRepository;

	@Autowired
	private EpisodeRepository episodeRepository;

	@Autowired
	private VideoProcessingService videoProcessingService;

	private final String THUMBNAIL_PATH = "D:/videos/thumbnails/";

	@Autowired
	private CloudinaryService cloudinaryService;

	@PostMapping("/episode")
	public String uploadEpisode(@RequestParam Long storyId, @RequestParam int episodeNumber, @RequestParam String title,
			@RequestParam MultipartFile file, @RequestParam MultipartFile thumbnail) {

		try {

			Story story = storyRepository.findById(storyId).orElseThrow(() -> new RuntimeException("Story not found"));

			// Upload video
			String videoUrl = cloudinaryService.uploadVideo(file);

			// Upload thumbnail
			String thumbnailUrl = cloudinaryService.uploadImage(thumbnail);

			Episode episode = new Episode();

			episode.setStory(story);
			episode.setEpisodeNumber(episodeNumber);
			episode.setTitle(title);
			episode.setVideoUrl(videoUrl);
			episode.setThumbnail(thumbnailUrl);

			episodeRepository.save(episode);

			return "Episode uploaded successfully";

		} catch (Exception e) {
			return "Upload failed: " + e.getMessage();
		}
	}

//	@PostMapping("/episode")
//	public String uploadEpisode(@RequestParam Long storyId, @RequestParam int episodeNumber, @RequestParam String title,
//			@RequestParam MultipartFile file, @RequestParam MultipartFile thumbnail) {
//
//		try {
//
//			Story story = storyRepository.findById(storyId).orElseThrow(() -> new RuntimeException("Story not found"));
//
//			String videoUrl = videoProcessingService.processVideo(file, story.getId(), episodeNumber);
//
//			Files.createDirectories(Paths.get(THUMBNAIL_PATH));
//
//			String thumbName = UUID.randomUUID() + "_" + thumbnail.getOriginalFilename();
//
//			Path thumbPath = Paths.get(THUMBNAIL_PATH, thumbName);
//
//			Files.copy(thumbnail.getInputStream(), thumbPath, StandardCopyOption.REPLACE_EXISTING);
//
//			Episode episode = new Episode();
//
//			episode.setStory(story);
//			episode.setEpisodeNumber(episodeNumber);
//			episode.setTitle(title);
//			episode.setVideoUrl(videoUrl);
//			episode.setThumbnail("/thumbnails/" + thumbName);
//
//			episodeRepository.save(episode);
//
//			return "Episode uploaded successfully";
//
//		} catch (Exception e) {
//
//			return "Upload failed: " + e.getMessage();
//		}
//	}
}