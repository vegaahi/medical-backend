package com.medical.entity;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
@JoinColumn(name = "chapter_number", nullable = false) 
private Chapter chapter; 
@Column(name = "subchapter_number", nullable = false) 
private Integer subchapterNumber; 
@Enumerated(EnumType.STRING) 
@Column(name = "content_type", nullable = false) 
private ContentType contentType;  // Enum for 'text' or 'image' 
@Column(name = "content", nullable = false) 
private String content; 
// Getters and Setters 
}
 enum ContentType { 
TEXT, 
IMAGE 
}