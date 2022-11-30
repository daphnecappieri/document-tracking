package com.daphne.visiblethread.documenttracking.controller;


import com.daphne.visiblethread.documenttracking.service.DocumentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@Slf4j
public class DocumentController {

    private final DocumentService service;

    DocumentController(DocumentService service) {
        this.service = service;
    }


    /**
     * Upload a document to be stored.
     *
     * @param file      Text file to be uploaded
     * @param userEmail Email address of user
     * @return ResponseEntity
     */
    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("email") String userEmail) {
        try {
            service.store(file, userEmail);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Check the top 10 word frequency in a document.
     *
     * @param file Text file to be analysed for word frequency
     * @return ResponseEntity Map of the top frequent words in the document
     */
    @PostMapping("/wordFrequency")
    public ResponseEntity<Map<String, Integer>> handleWordFrequency(@RequestParam("file") MultipartFile file) {
        try {
            Map<String, Integer> wordFreq = service.findWordFrequency(file);
            return new ResponseEntity<>(wordFreq, HttpStatus.OK);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
