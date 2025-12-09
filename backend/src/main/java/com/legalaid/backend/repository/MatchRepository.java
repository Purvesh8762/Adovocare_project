package com.legalaid.backend.repository;

import com.legalaid.backend.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findByCaseId(Long caseId);
    List<Match> findByProfileId(Long profileId);
}
