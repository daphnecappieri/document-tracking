package com.daphne.visiblethread.documenttracking.repo;

import com.daphne.visiblethread.documenttracking.model.Document;
import com.daphne.visiblethread.documenttracking.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {

}
