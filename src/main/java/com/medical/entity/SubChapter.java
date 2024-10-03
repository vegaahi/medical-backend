package com.medical.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "sub_chapter")
public class SubChapter { 
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id; 
    
    @ManyToOne 
    @JoinColumn(name = "chapter_number", nullable = false)  // Maps to chapterNumber in Chapter
    private Chapter chapter; 
    
    @Column(name = "subchapter_number", nullable = false) 
    private Integer subchapterNumber; 
    
    @Enumerated(EnumType.STRING) 
    @Column(name = "content_type", nullable = false) 
    private ContentType contentType;  // Enum for 'text' or 'image' 
    
    @Column(name = "content", nullable = false) 
    private String content; 
}
