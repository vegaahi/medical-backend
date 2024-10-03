package com.medical.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.medical.entity.SubChapter;
import com.medical.service.SubChapterService;

import java.util.List;

@RestController
@RequestMapping("/admins/subchapter/")
public class SubChapterController {

    @Autowired
    private SubChapterService subChapterService;

    // Add a new subchapter
    @PostMapping("/text/{sid}")
    public ResponseEntity<SubChapter> addSubChapter(@RequestBody SubChapter subChapter) {
        SubChapter savedSubChapter = subChapterService.saveSubChapter(subChapter);
        return ResponseEntity.ok(savedSubChapter);
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

