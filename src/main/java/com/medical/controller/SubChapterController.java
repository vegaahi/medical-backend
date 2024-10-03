package com.medical.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import com.medical.entity.SubChapter;
import com.medical.entity.Chapter;
import com.medical.entity.ContentType;
import com.medical.service.SubChapterService;
import com.medical.repository.ChapterRepository;

import org.springframework.beans.factory.annotation.Value;

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

    // Directory where images will be stored
    @Value("${image.upload.dir}") // Ensure this path is set in application.properties
    private String uploadDir;

    // Add a new subchapter (Text)
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
<<<<<<< HEAD
    
    
    // Add a new subchapter
    @PostMapping("/image/{sid}")
    public ResponseEntity<SubChapter> addSubChapterByImage(@RequestBody SubChapter subChapter) {
        SubChapter savedSubChapter = subChapterService.saveSubChapter(subChapter);
        return ResponseEntity.ok(savedSubChapter);
    }

    // Update an existing subchapter
    @PutMapping("/{sid}")
    public ResponseEntity<String> updateSubChapter(@PathVariable Long subChapterId, @RequestBody SubChapter subChapterDetails) {
        SubChapter existingSubChapter = subChapterService.getSubChapterById(subChapterId);
        
        if (existingSubChapter == null) {
            return ResponseEntity.notFound().build(); // Handle case where subchapter doesn't exist
        }

        // Update the fields of the existing subchapter with the new data
        existingSubChapter.setChapter(subChapterDetails.getChapter());
        existingSubChapter.setSubchapterTitle(subChapterDetails.getSubchapterTitle());
        existingSubChapter.setSubchapterNumber(subChapterDetails.getSubchapterNumber());
        // Update any other fields that are necessary

        // Save the updated subchapter
        subChapterService.saveSubChapter(existingSubChapter);

        return ResponseEntity.ok("SubChapter updated successfully");
=======

    // Add a new subchapter with image
    @PostMapping("/image")
    public ResponseEntity<String> uploadSubChapterWithImage(
            @RequestParam("chapterId") Long chapterId,
            @RequestParam("subchapterNumber") Integer subchapterNumber,
            @RequestParam("contentType") ContentType contentType,
            @RequestParam("subchapterTitle") String subchapterTitle,
            @RequestParam("file") MultipartFile file) {

        // Ensure the chapter exists
        Chapter chapter = chapterRepository.findById(chapterId)
                .orElseThrow(() -> new RuntimeException("Chapter not found with id: " + chapterId));

        // Validate content type
        if (contentType == ContentType.IMAGE) {
            try {
                // Save the file in the upload directory
                String fileName = file.getOriginalFilename();
                Path filePath = Paths.get(uploadDir + File.separator + fileName);
                Files.write(filePath, file.getBytes());

                // Create a SubChapter and save the file name in the "content" field
                SubChapter subChapter = new SubChapter();
                subChapter.setSubchapterNumber(subchapterNumber);
                subChapter.setContent(fileName); // Saving image title (file name)
                subChapter.setContentType(contentType);
                subChapter.setSubchapterTitle(subchapterTitle);
                subChapter.setChapter(chapter); // Set the chapter reference

                // Save SubChapter to the database
                subChapterService.saveSubChapter(subChapter);

                return ResponseEntity.ok("Image uploaded and subchapter saved.");
            } catch (IOException e) {
                return ResponseEntity.status(500).body("Error while saving image.");
            }
        } else {
            return ResponseEntity.badRequest().body("ContentType must be IMAGE for file upload.");
        }
    }

    @PutMapping("/image/update/{chapterId}/{subchapterNumber}")
    public ResponseEntity<String> updateSubChapterImage(
            @PathVariable Long chapterId,
            @PathVariable Integer subchapterNumber,
            @RequestParam("file") MultipartFile file) {

        // Fetch the chapter by ID
        Chapter chapter = chapterRepository.findById(chapterId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chapter not found with id: " + chapterId));

        // Fetch the subchapter by chapter and subchapter number
        SubChapter subChapter = subChapterService.getSubChapterByChapterAndSubchapterNumber(chapter, subchapterNumber);
        if (subChapter == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "SubChapter not found for chapter: " + chapterId + " and subchapter: " + subchapterNumber);
        }

        // Validate file content type to ensure it's an image
        if (!file.getContentType().startsWith("image/")) {
            return ResponseEntity.badRequest().body("The uploaded file must be an image.");
        }

        try {
            // Delete the old image file (optional)
            String oldImage = subChapter.getContent();
            if (oldImage != null) {
                Path oldImagePath = Paths.get(uploadDir + File.separator + oldImage);
                Files.deleteIfExists(oldImagePath);
            }

            // Save the new image in the upload directory
            String newFileName = file.getOriginalFilename();
            Path newFilePath = Paths.get(uploadDir + File.separator + newFileName);
            Files.write(newFilePath, file.getBytes());

            // Update subchapter with the new image filename
            subChapter.setContent(newFileName); // Save new image filename in content field
            subChapter.setContentType(ContentType.IMAGE);

            // Save the updated subchapter
            subChapterService.saveSubChapter(subChapter);

            return ResponseEntity.ok("SubChapter image updated successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while updating image: " + e.getMessage());
        }
>>>>>>> origin/adminchanges
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
