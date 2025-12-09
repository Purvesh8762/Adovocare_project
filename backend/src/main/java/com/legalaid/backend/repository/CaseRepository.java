  //java to msql [can be connect]**baat krna 
package com.legalaid.backend.repository;

import com.legalaid.backend.model.Case;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CaseRepository extends JpaRepository<Case, Long> {
    List<Case> findByUserId(Long userId);
}
