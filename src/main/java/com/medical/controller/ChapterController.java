package com.medical.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.RestController;

import com.medical.entity.Admin;
import com.medical.entity.Chapter;
import com.medical.service.AdminService;
import com.medical.service.ChapterService;

@RestController

@RequestMapping("/admins/chapter/")
public class ChapterController {

	
	@Autowired
    private ChapterService chapterService;

    // Add a new chapter
    @PostMapping("/{cid}")
    public ResponseEntity<Chapter> addChapter(@RequestBody Chapter chapter) {
        Chapter savedChapter = chapterService.saveChapter(chapter);
        return ResponseEntity.ok(savedChapter);
    }

    // Edit an existing chapter
    @PutMapping("/{cid}")
    public ResponseEntity<Chapter> editChapter(@PathVariable Long id, @RequestBody Chapter updatedChapter) {
        Chapter editedChapter = chapterService.updateChapter(id, updatedChapter);
        return ResponseEntity.ok(editedChapter);
    }

    // Get all chapters (optional, for convenience)
    @GetMapping
    public ResponseEntity<List<Chapter>> getAllChapters() {
        List<Chapter> chapters = chapterService.getAllChapters();
        return ResponseEntity.ok(chapters);
    }

   
}

