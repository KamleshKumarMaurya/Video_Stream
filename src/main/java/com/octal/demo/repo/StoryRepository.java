package com.octal.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.octal.demo.entity.Story;

public interface StoryRepository extends JpaRepository<Story, Long> {
}