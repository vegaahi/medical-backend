package com.medical.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.medical.entity.Chapter;
import com.medical.entity.ContentType;
import com.medical.entity.SubChapter;

public interface SubChapterRepository extends JpaRepository<SubChapter, Long> {

	Optional<SubChapter> findByChapterAndSubchapterNumberAndContentType(Chapter chapter, Integer subchapterNumber,ContentType contentType);

	@Query("SELECT COUNT(sc) FROM SubChapter sc WHERE sc.chapter.chapterNumber = :chapterId AND sc.subchapterNumber = :subchapterNumber AND sc.contentType = :contentType")
    int countImagesForSubChapter(@Param("chapterId") Long chapterId, 
                                 @Param("subchapterNumber") Integer subchapterNumber, 
                                 @Param("contentType") ContentType contentType);

	@Query("SELECT sc FROM SubChapter sc WHERE sc.content LIKE CONCAT(:content, '%')")
	Optional<SubChapter> findSubChapterByContentStartingWith(@Param("content") String content);

	

	

}
