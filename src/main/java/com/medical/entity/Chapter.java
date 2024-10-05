package com.medical.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "chapter")
public class Chapter { 
@Id 
@GeneratedValue(strategy = GenerationType.IDENTITY) 
private Long id; 

@Column(name = "chapter_number", nullable = false) 
private Integer chapterNumber; 

@Column(name = "title", nullable = false) 
private String title; 

@OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL, orphanRemoval = true)
@JsonManagedReference  // Indicates this is the forward part of the reference
private List<SubChapter> subChapters;

public Chapter orElseThrow(Object object) {
	// TODO Auto-generated method stub
	return null;
}


// Getters and Setters 
}
