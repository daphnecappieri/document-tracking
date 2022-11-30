package com.daphne.visiblethread.documenttracking.service;

import com.daphne.visiblethread.documenttracking.model.Document;
import com.daphne.visiblethread.documenttracking.repo.DocumentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.mockito.ArgumentMatchers.any;

class DocumentServiceImplTest {

    private DocumentService documentService;

    private DocumentRepository mockDocumentRepository;

    @BeforeEach
    void setup() {
        mockDocumentRepository = Mockito.mock(DocumentRepository.class);
        documentService = new DocumentServiceImpl(mockDocumentRepository);
    }

    @Test
    void verifyDocumentStore() throws IOException {
        Mockito.when(mockDocumentRepository.save(any())).thenReturn(new Document());
        documentService.store(getTestFile(),"");
        Mockito.verify(mockDocumentRepository, Mockito.times(1))
                .save(any());
    }

    private MultipartFile getTestFile() throws FileNotFoundException {
        Path path = Paths.get("/resources/test.txt");
        byte[] content = null;
        try {
            content = Files.readAllBytes(path);
        } catch (final IOException e) {
        }
        return  new MockMultipartFile("test.txt",
                "test.txt",  "text/plain", content);
    }

}