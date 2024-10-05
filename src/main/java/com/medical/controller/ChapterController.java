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

   

    // Get all chapters (optional, for convenience)
    @GetMapping
    public ResponseEntity<List<Chapter>> getAllChapters() {
        List<Chapter> chapters = chapterService.getAllChapters();
        return ResponseEntity.ok(chapters);
    }
    
 // Update an existing chapter
    @PutMapping("/{cid}")
    public ResponseEntity<Chapter> updateChapter(@PathVariable("cid") Long cid, @RequestBody Chapter chapter) {
        Chapter existingChapter = chapterService.getChapterById(cid);
        
        if (existingChapter == null) {
            return ResponseEntity.notFound().build(); // Handle case where chapter doesn't exist
        }
        
        // Update the fields of the existing chapter with the new data from the request body
        existingChapter.setTitle(chapter.getTitle());
        existingChapter.setChapterNumber(chapter.getChapterNumber());
        // Update any other fields that are required for the chapter

        Chapter updatedChapter = chapterService.saveChapter(existingChapter);
        return ResponseEntity.ok(updatedChapter);
    }
 // Get a chapter by ID
    @GetMapping("/{cid}")
    public ResponseEntity<Chapter> getChapterById(@PathVariable("cid") Long cid) {
        Chapter chapter = chapterService.getChapterByChapterNumber(cid);
        
        if (chapter == null) {
            return ResponseEntity.notFound().build(); // Handle case where chapter doesn't exist
        }
        
        return ResponseEntity.ok(chapter); // Return the found chapter
    }
   
   
}

