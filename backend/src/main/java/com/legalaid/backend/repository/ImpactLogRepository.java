package com.legalaid.backend.repository;

import com.legalaid.backend.model.ImpactLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImpactLogRepository extends JpaRepository<ImpactLog, Long> {
    List<ImpactLog> findByProfileId(Long profileId);
}


