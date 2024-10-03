package com.medical.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.medical.entity.SubChapter;
import com.medical.repository.SubChapterRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SubChapterService {

    @Autowired
    private SubChapterRepository subChapterRepository;

    public SubChapter saveSubChapter(SubChapter subChapter) {
        return subChapterRepository.save(subChapter);
    }

    public SubChapter updateSubChapter(Long id, SubChapter updatedSubChapter) {
        Optional<SubChapter> existingSubChapterOpt = subChapterRepository.findById(id);
        if (existingSubChapterOpt.isPresent()) {
            SubChapter existingSubChapter = existingSubChapterOpt.get();
            existingSubChapter.setSubchapterNumber(updatedSubChapter.getSubchapterNumber());
            existingSubChapter.setContentType(updatedSubChapter.getContentType());
            existingSubChapter.setContent(updatedSubChapter.getContent());
            return subChapterRepository.save(existingSubChapter);
        }
        throw new RuntimeException("SubChapter not found");
    }

    public List<SubChapter> getAllSubChapters() {
        return subChapterRepository.findAll();
    }

    public SubChapter getSubChapterById(Long id) {
        return subChapterRepository.findById(id).orElseThrow(() -> new RuntimeException("SubChapter not found"));
    }
}
