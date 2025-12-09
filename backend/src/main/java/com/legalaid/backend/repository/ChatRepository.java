package com.legalaid.backend.repository;

import com.legalaid.backend.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findByMatchIdOrderByTimestampAsc(Long matchId);
}

