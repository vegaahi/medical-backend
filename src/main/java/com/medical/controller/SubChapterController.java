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
@RequestMapping()
public class SubChapterController {

    @Autowired
    private SubChapterService subChapterService;

    @Autowired
    private ChapterRepository chapterRepository;

    // Directory where images will be stored
    @Value("${image.upload.dir}") // Ensure this path is set in application.properties
    private String uploadDir;

    // Add a new subchapter (Text)
    @PostMapping("/admins/subchapter/text")
    public ResponseEntity<String> createSubChapter(@RequestBody SubChapter subChapter, @RequestParam Long chapterNumber) {
    	Chapter chapter = chapterRepository.findByChapterNumber(chapterNumber)
                .orElseThrow(() -> new RuntimeException("Chapter not found with id: " + chapterNumber));
        
        // Set the chapter reference in the subchapter
        subChapter.setChapter(chapter);

        // Save the subchapter
        subChapterService.saveSubChapter(subChapter);

        return ResponseEntity.ok("SubChapter created successfully");
    }

    @PostMapping("/admins/subchapter/image")
    public ResponseEntity<String> uploadSubChapterWithImage(
            @RequestParam("chapterId") Long chapterId,
            @RequestParam("subchapterNumber") Integer subchapterNumber,
            @RequestParam("contentType") ContentType contentType,
            @RequestParam("subchapterTitle") String subchapterTitle,
            @RequestParam("file") MultipartFile file) {

        // Ensure the chapter exists
        Chapter chapter = chapterRepository.findByChapterNumber(chapterId)
                .orElseThrow(() -> new RuntimeException("Chapter not found with id: " + chapterId));

        // Validate content type
        if (contentType == ContentType.IMAGE) {
            try {
                // Extract file extension
                String originalFileName = file.getOriginalFilename();
                String extension = "";
                if (originalFileName != null && originalFileName.contains(".")) {
                    extension = originalFileName.substring(originalFileName.lastIndexOf("."));
                }

                // Count existing images for this chapter and subchapter
                int imageCount = subChapterService.countImagesForSubChapter(chapterId, subchapterNumber) + 1;

                // Construct new file name as Image_{chapterNumber}_{subchapterNumber}_{imageCount}.{extension}
                String newFileName = "Image_" + chapter.getChapterNumber() + "_" + subchapterNumber + "_" + imageCount + extension;

                // Save the file in the upload directory with the new file name
                Path filePath = Paths.get(uploadDir + File.separator + newFileName);
                Files.write(filePath, file.getBytes());

                // Create a SubChapter and save the new file name in the "content" field
                SubChapter subChapter = new SubChapter();
                subChapter.setSubchapterNumber(subchapterNumber);
                subChapter.setContent(newFileName); // Saving the new file name
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


    // Update an existing subchapter (Text)
    @PutMapping("/admins/subchapter/{contentType}/{cid}/{subChapterId}")
    public ResponseEntity<String> updateSubChapter(
            @PathVariable Long cid,
            @PathVariable Integer subChapterId, 
            @PathVariable ContentType contentType,
            @RequestBody SubChapter subChapterDetails) {
    System.out.println(cid+" "+subChapterId);
        
    	 Chapter chapter = chapterRepository.findByChapterNumber(cid)
                 .orElseThrow(() -> new RuntimeException("Chapter not found with id: " + cid));
         
// Set the chapter reference

    	 // Fetch the subchapter by chapter and subchapter number
         SubChapter subChapter = subChapterService.getSubChapterByChapterAndSubchapterNumberAndContentType(chapter, subChapterId,contentType);
         if (subChapter == null) {
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "SubChapter not found for chapter: " + cid + " and subchapter: " + subChapterId);
         }
//        SubChapter existingSubChapter = subChapterService.getSubChapterById(subChapterId);
        
       
        // Update the fields of the existing subchapter with the new data
        // subChapter.setChapter(subChapterDetails.getChapter());
         subChapter.setSubchapterTitle(subChapterDetails.getSubchapterTitle());
         subChapter.setContent(subChapterDetails.getContent());
        // subChapter.setSubchapterNumber(subChapterDetails.getSubchapterNumber());
        // Update any other fields that are necessary

        // Save the updated subchapter
        subChapterService.saveSubChapter(subChapter);

        return ResponseEntity.ok("SubChapter updated successfully");
    }
    
    
    @GetMapping("/admins/subchapter/{contentType}/{cid}/{subChapterId}")
    public SubChapter getSubChapterByContentTypeText(

    		@PathVariable Long cid,
            @PathVariable Integer subChapterId, 
            @PathVariable ContentType contentType
    		
    		){
    	
    	 Chapter chapter = chapterRepository.findByChapterNumber(cid)
                 .orElseThrow(() -> new RuntimeException("Chapter not found with id: " + cid));
    	
    	 SubChapter subChapter = subChapterService.getSubChapterByChapterAndSubchapterNumberAndContentType(chapter, subChapterId,contentType);
         if (subChapter == null) {
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "SubChapter not found for chapter: " + cid + " and subchapter: " + subChapterId);
         }
         return subChapter;
    }


    @PutMapping("/admins/subchapter/image/{chapterId}/{subchapterNumber}/{imageNumber}")
    public ResponseEntity<String> updateSubChapterWithImage(
            @PathVariable("chapterId") Long chapterId,
            @PathVariable("subchapterNumber") Integer subchapterNumber,
            @PathVariable("imageNumber") String imageNumber, // Path variable for the image name

            @RequestParam("file") MultipartFile file) {

    	String existingImageName = "Image_"+chapterId+"_"+subchapterNumber+"_"+imageNumber;
        // Ensure the chapter exists
        Chapter chapter = chapterRepository.findByChapterNumber(chapterId)
                .orElseThrow(() -> new RuntimeException("Chapter not found with id: " + chapterId));

    
            try {
                // Check if the image exists and update the image
                SubChapter existingSubChapter = subChapterService.findSubChapterByContent(existingImageName)
                        .orElseThrow(() -> new RuntimeException("Image with name " + existingImageName + " not found"));

                // Extract file extension
                String originalFileName = file.getOriginalFilename();
                String extension = "";
                if (originalFileName != null && originalFileName.contains(".")) {
                    extension = originalFileName.substring(originalFileName.lastIndexOf("."));
                }

                // Delete the old image file (optional)
                Path existingFilePath = Paths.get(uploadDir + File.separator + existingImageName);
                Files.deleteIfExists(existingFilePath);

                // Generate new file name, keeping the same naming pattern
                String newFileName = "Image_" + chapter.getChapterNumber() + "_" + subchapterNumber + "_" 
                                     + imageNumber + extension;

                // Save the new file in the upload directory
                Path filePath = Paths.get(uploadDir + File.separator + newFileName);
                Files.write(filePath, file.getBytes());

                // Update SubChapter content with new image file name
                existingSubChapter.setContent(newFileName);
//                existingSubChapter.setSubchapterTitle(subchapterTitle); // Update title if necessary
           

                // Save the updated SubChapter
                subChapterService.saveSubChapter(existingSubChapter);

                return ResponseEntity.ok("Image updated successfully.");
            } catch (IOException e) {
                return ResponseEntity.status(500).body("Error while saving image.");
            }
        } 
    

    
    


    // Get a single subchapter by ID (optional)
    @GetMapping("/admins/subchapter/{id}")
    public ResponseEntity<SubChapter> getSubChapterById(@PathVariable Long id) {
        SubChapter subChapter = subChapterService.getSubChapterById(id);
        return ResponseEntity.ok(subChapter);
    }

    
    
    
    @DeleteMapping("/admins/subchapter/{contentType}/{cid}/{subChapterId}")
    public ResponseEntity<String> deleteSubChapter(
            @PathVariable Long cid,
            @PathVariable Integer subChapterId, 
            @PathVariable ContentType contentType) {

    	 // Fetch the subchapter by chapter and subchapter number
         SubChapter subChapter = getSubChapterByContentTypeText(cid,subChapterId,contentType);
         if (subChapter == null) {
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "SubChapter not found for chapter: " + cid + " and subchapter: " + subChapterId);
         }else {
        subChapterService.deleteSubChapter(subChapter);
         }
        return ResponseEntity.ok("SubChapter deleted successfully");
    }
    

    @DeleteMapping("/admins/subchapter/image/{chapterId}/{subchapterNumber}/{imageNumber}")
       public ResponseEntity<String> deleteSubChapterImage(
               @PathVariable("chapterId") Long chapterId,
               @PathVariable("subchapterNumber") Integer subchapterNumber,
               @PathVariable("imageNumber") String imageNumber) {
           
           String existingImageName = "Image_" + chapterId + "_" + subchapterNumber + "_" + imageNumber;

           // Ensure the chapter exists
           Chapter chapter = chapterRepository.findByChapterNumber(chapterId)
                   .orElseThrow(() -> new RuntimeException("Chapter not found with id: " + chapterId));

           try {
               // Check if the image exists and delete the image
               SubChapter existingSubChapter = subChapterService.findSubChapterByContent(existingImageName)
                       .orElseThrow(() -> new RuntimeException("Image with name " + existingImageName + " not found"));

               // Delete the old image file
               Path existingFilePath = Paths.get(uploadDir + File.separator + existingImageName);
               Files.deleteIfExists(existingFilePath);

               // Remove the subchapter entry from the database
               subChapterService.deleteSubChapter(existingSubChapter);

               return ResponseEntity.ok("Image deleted successfully.");
           } catch (IOException e) {
               return ResponseEntity.status(500).body("Error while deleting image.");
           }
       }

   
       

    

}
