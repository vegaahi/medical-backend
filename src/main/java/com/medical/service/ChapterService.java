package com.medical.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.entity.Chapter;
import com.medical.repository.ChapterRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ChapterService {

    @Autowired
    private ChapterRepository chapterRepository;

    public Chapter saveChapter(Chapter chapter) {
        return chapterRepository.save(chapter);
    }

    public Chapter updateChapter(Long id, Chapter updatedChapter) {
        Optional<Chapter> existingChapterOpt = chapterRepository.findById(id);
        if (existingChapterOpt.isPresent()) {
            Chapter existingChapter = existingChapterOpt.get();
            existingChapter.setChapterNumber(updatedChapter.getChapterNumber());
            existingChapter.setTitle(updatedChapter.getTitle());
            existingChapter.setSubChapters(updatedChapter.getSubChapters());
            return chapterRepository.save(existingChapter);
        }
        throw new RuntimeException("Chapter not found");
    }

    public List<Chapter> getAllChapters() {
        return chapterRepository.findAll();
    }

    public Chapter getChapterById(Long id) {
        return chapterRepository.findById(id).orElseThrow(() -> new RuntimeException("Chapter not found"));
    }
    public Chapter getChapterByChapterNumber(long chapterNumber) {
        return chapterRepository.findByChapterNumber(chapterNumber); // Call the repo method
    }


}
