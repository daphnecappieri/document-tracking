package com.daphne.visiblethread.documenttracking.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;


public interface DocumentService {

    void store(MultipartFile file, String userEmail) throws IOException;

    Map<String, Integer> findWordFrequency(MultipartFile file) throws IOException;
}
