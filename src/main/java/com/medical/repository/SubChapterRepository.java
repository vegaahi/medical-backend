package com.medical.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.medical.entity.SubChapter;

public interface SubChapterRepository extends JpaRepository<SubChapter, Long> {
}
