package com.medical.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medical.entity.Chapter;
import com.medical.entity.SubChapter;

public interface SubChapterRepository extends JpaRepository<SubChapter, Long> {

	Optional<SubChapter> findByChapterAndSubchapterNumber(Chapter chapter, Integer subchapterNumber);
}
