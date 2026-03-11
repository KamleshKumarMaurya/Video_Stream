package com.octal.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.octal.demo.dto.EpisodeDto;
import com.octal.demo.entity.Episode;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {

	List<Episode> findByStoryIdOrderByEpisodeNumber(Long storyId);

	@Query("""
			SELECT new com.octal.demo.dto.EpisodeDto(
			e.id,
			e.episodeNumber,
			e.title,
			e.videoUrl,e.thumbnail
			)
			FROM Episode e
			WHERE e.story.id = :storyId
			ORDER BY e.episodeNumber
			""")
	List<EpisodeDto> findEpisodesByStoryId(Long storyId);
}