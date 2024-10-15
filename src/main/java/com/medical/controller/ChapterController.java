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
    public ResponseEntity<?> addChapter(@RequestBody Chapter chapter) {
        try {
            Chapter savedChapter = chapterService.saveChapter(chapter);
            return ResponseEntity.ok(savedChapter);
        } catch (Exception ex) {
            return new ResponseEntity<>("Failed to add chapter: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all chapters
    @GetMapping
    public ResponseEntity<?> getAllChapters() {
        try {
            List<Chapter> chapters = chapterService.getAllChapters();
            return ResponseEntity.ok(chapters);
        } catch (Exception ex) {
            return new ResponseEntity<>("Failed to fetch chapters: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update an existing chapter
    @PutMapping("/{cid}")
    public ResponseEntity<?> updateChapter(@PathVariable("cid") Long cid, @RequestBody Chapter chapter) {
        try {
            Chapter existingChapter = chapterService.getByChapterNumber(cid);
            if (existingChapter == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Chapter not found");
            }
            existingChapter.setTitle(chapter.getTitle());
            // Update other fields if needed
            Chapter updatedChapter = chapterService.saveChapter(existingChapter);
            return ResponseEntity.ok(updatedChapter);
        } catch (Exception ex) {
            return new ResponseEntity<>("Failed to update chapter: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get a chapter by ID
    @GetMapping("/{cid}")
    public ResponseEntity<?> getChapterById(@PathVariable("cid") Long cid) {
        try {
            Chapter chapter = chapterService.getByChapterNumber(cid);
            if (chapter == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Chapter not found");
            }
            return ResponseEntity.ok(chapter);
        } catch (Exception ex) {
            return new ResponseEntity<>("Error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/{chapterNumber}")
    public ResponseEntity<Void> deleteChapter(@PathVariable Long chapterNumber) {
        try {
            chapterService.deleteChapterByChapterNumber(chapterNumber);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
   
}
