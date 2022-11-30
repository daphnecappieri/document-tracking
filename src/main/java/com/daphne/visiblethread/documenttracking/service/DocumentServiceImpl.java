package com.daphne.visiblethread.documenttracking.service;

import com.daphne.visiblethread.documenttracking.model.Document;
import com.daphne.visiblethread.documenttracking.repo.DocumentRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;


@Slf4j
@Service
public class DocumentServiceImpl implements DocumentService {

    @Value("#{${wordsToExclude}}")
    private List<String> wordsToExclude;
    Map<String, Integer> wordFrequencyMap;
    private final DocumentRepository documentRepository;

    DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public void store(MultipartFile file, String userEmail) throws IOException {

        Document doc = new Document();
        doc.setName(file.getOriginalFilename());
        doc.setWordCount(getMockWordCount());
        doc.setFile(file.getBytes());

        //TODO verify user in repo
        doc.setUserEmail(userEmail);
        documentRepository.save(doc);
    }


    public Map<String, Integer> findWordFrequency(MultipartFile file) throws IOException {

        wordFrequencyMap = new HashMap<>();

        InputStream inputStream = file.getInputStream();
        new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines()
                .forEach(this::collectWords);

        // Exclude list of words
        if (CollectionUtils.isNotEmpty(wordsToExclude)) {
            for (String word : wordsToExclude
            ) {
                wordFrequencyMap.remove(word.toLowerCase());
            }
        }
        return sortMap();

    }

    private void collectWords(String line) {
        String line2 = line.replaceAll("[(?!:;.,)]", " ");
        String[] wordsInLine = line2.toLowerCase().split("\\s+");
        for (String word : wordsInLine
        ) {
            if (!wordFrequencyMap.containsKey(word)) {
                wordFrequencyMap.put(word, 1);
            } else {
                int value = wordFrequencyMap.get(word);
                wordFrequencyMap.put(word, value + 1);
            }
        }
    }

    private Map<String, Integer> sortMap() {

        return wordFrequencyMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    private int getMockWordCount() {
        return (int) (100 + Math.random() * 5000);
    }
}
