package com.daphne.visiblethread.documenttracking.model;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "documents")
@Data
public class Document {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "name")
    private String name;

    @Lob
    @Column(name = "document")
    private byte[] file;

    @Column(name = "word_count")
    private int wordCount;

    @Column(name = "user_email")
    private String userEmail;

}
