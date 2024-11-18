package com.medical.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.medical.entity.Chapter;
import com.medical.entity.ContentType;

import com.medical.entity.NriDoctorEntity;

import com.medical.entity.SubChapter;
import com.medical.repository.SubChapterRepository;
import org.springframework.web.server.ResponseStatusException;

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

    public SubChapter getSubChapterByChapterAndSubchapterNumberAndContentType(Chapter chapter, Integer subchapterNumber, ContentType contentType) {
        return subChapterRepository.findByChapterAndSubchapterNumberAndContentType(chapter, subchapterNumber,contentType)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "SubChapter not found for given chapter and subchapter number"));
    }

	public int countImagesForSubChapter(Long chapterId, Integer subchapterNumber) {
		  return subChapterRepository.countImagesForSubChapter(chapterId, subchapterNumber, ContentType.IMAGE);
	}


	 public Optional<SubChapter> findSubChapterByContent(String content) {
	        return subChapterRepository.findSubChapterByContentStartingWith(content);
	    }


//	public void deleteSubChapter(SubChapter subChapter) {
//		
//   
//		subChapterRepository.delete(subChapter).orElseThrow(() -> new RuntimeException("SubChapter not found for given chapter and subchapter number"));
//       
//	}
	 public void deleteSubChapter(SubChapter subChapter) {
		    subChapterRepository.delete(subChapter);
		}

	 public void deleteSubChapterImage(Chapter chapter, Integer subChapterNumber) {
	        SubChapter subChapter = subChapterRepository.findByChapterAndSubchapterNumberAndContentType(chapter, subChapterNumber, ContentType.IMAGE)
	                .orElseThrow(() -> new RuntimeException("SubChapter image not found for given chapter and subchapter number"));
	        subChapterRepository.delete(subChapter);
	    }
	
	 public void deleteSubChapterImage(Chapter chapter, Integer subchapterNumber, String imageName) {
	        SubChapter subChapter = subChapterRepository.findByChapterAndSubchapterNumberAndContentType(
	                chapter, subchapterNumber, ContentType.IMAGE)
	                .orElseThrow(() -> new RuntimeException("SubChapter image not found for given chapter and subchapter number"));
	        
	        if (subChapter.getContent().equals(imageName)) {
	            subChapterRepository.delete(subChapter);
	        } else {
	            throw new RuntimeException("Image not found with name: " + imageName);
	        }
	    }
	
    


}
