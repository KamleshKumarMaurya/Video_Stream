package com.octal.demo.dto;

import lombok.Data;

@Data
public class EpisodeDto {

	private Long id;
	private int episodeNumber;
	private String title;
	private String videoUrl;
	private String thumbnail;

	public EpisodeDto(Long id, int episodeNumber, String title, String videoUrl,String thumbnail) {
		this.id = id;
		this.episodeNumber = episodeNumber;
		this.title = title;
		this.videoUrl = videoUrl;
		this.thumbnail = thumbnail;
	}

	// getters
}