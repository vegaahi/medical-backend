package com.medical.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medical.entity.Chapter;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {

	Optional<Chapter> findByChapterNumber(Long chapterNumber);
//	findByChapterNumber(long chapterNumber);
}
