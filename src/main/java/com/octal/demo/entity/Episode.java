package com.octal.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Episode {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int episodeNumber;

	private String title;

	private String videoUrl;

	private int duration;
	
	private String thumbnail;

	@ManyToOne
	@JoinColumn(name = "story_id")
	private Story story;
}
