package com.medical.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.medical.entity.SubChapter;
import com.medical.service.SubChapterService;
import com.medical.entity.Chapter;
import com.medical.entity.ContentType;
import com.medical.repository.ChapterRepository;
import com.medical.repository.SubChapterRepository;
import org.springframework.beans.factory.annotation.Value;


import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;

@RestController
@RequestMapping("/admins/subchapter/")
public class SubChapterController {

    @Autowired
    private SubChapterService subChapterService;
    
    @Autowired
    private ChapterRepository chapterRepository;

    // Add a new subchapter
//    @PostMapping("/text/{sid}")
//    public ResponseEntity<SubChapter> addSubChapter(@RequestBody SubChapter subChapter) {
//        SubChapter savedSubChapter = subChapterService.saveSubChapter(subChapter);
//        return ResponseEntity.ok(savedSubChapter);
//    }
    
    @PostMapping("/text")
    public ResponseEntity<String> createSubChapter(@RequestBody SubChapter subChapter, @RequestParam Long chapterId) {
        Chapter chapter = chapterRepository.findById(chapterId)
                .orElseThrow(() -> new RuntimeException("Chapter not found with id: " + chapterId));
        
        // Set the chapter reference in the subchapter
        subChapter.setChapter(chapter);

        // Save the subchapter
        subChapterService.saveSubChapter(subChapter);

        return ResponseEntity.ok("SubChapter created successfully");
    }
    
    // Add a new subchapter
    @PostMapping("/image/{sid}")
    public ResponseEntity<SubChapter> addSubChapterByImage(@RequestBody SubChapter subChapter) {
        SubChapter savedSubChapter = subChapterService.saveSubChapter(subChapter);
        return ResponseEntity.ok(savedSubChapter);
    }

    // Edit an existing subchapter
    @PutMapping("/edit/{id}")
    public ResponseEntity<SubChapter> editSubChapter(@PathVariable Long id, @RequestBody SubChapter updatedSubChapter) {
        SubChapter editedSubChapter = subChapterService.updateSubChapter(id, updatedSubChapter);
        return ResponseEntity.ok(editedSubChapter);
    }

    // Get all subchapters (optional, for convenience)
    @GetMapping
    public ResponseEntity<List<SubChapter>> getAllSubChapters() {
        List<SubChapter> subChapters = subChapterService.getAllSubChapters();
        return ResponseEntity.ok(subChapters);
    }

    // Get a single subchapter by ID (optional)
    @GetMapping("/{id}")
    public ResponseEntity<SubChapter> getSubChapterById(@PathVariable Long id) {
        SubChapter subChapter = subChapterService.getSubChapterById(id);
        return ResponseEntity.ok(subChapter);
    }
}

