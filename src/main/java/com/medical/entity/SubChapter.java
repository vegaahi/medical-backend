package com.medical.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
    name = "sub_chapter",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"chapter_number", "subchapter_number", "subchapter_title"})
    }
)
public class SubChapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "chapter_number", nullable = false)
    @JsonBackReference // Maps to chapterNumber in Chapter
    private Chapter chapter;

    @Column(name = "subchapter_number", nullable = false)
    private Integer subchapterNumber;

    @Column(name = "subchapter_title", nullable = false)
    private String subchapterTitle;

    @Enumerated(EnumType.STRING)
    @Column(name = "content_type", nullable = false)
    private ContentType contentType; // Enum for 'text' or 'image'

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content;
}
