package com.legalaid.backend.repository;

import com.legalaid.backend.model.Match;   // <‑‑ make sure it imports model.Match
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {

    // you want to query by the case or profile.
    // aCase is the field name, profile is the field name.

    // if you search by Case entity:
    // List<Match> findByACase(Case aCase);

    // if you search by case id:
    List<Match> findByACase_Id(Long caseId);

    // if you search by Profile entity:
    // List<Match> findByProfile(Profile profile);

    // if you search by profile id:
    List<Match> findByProfile_Id(Long profileId);
}
