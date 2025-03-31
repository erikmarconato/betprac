package com.betprac.betprac.FootballAPI.repositories;

import com.betprac.betprac.FootballAPI.entities.MatchesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MatchesRepository extends JpaRepository<MatchesEntity, Long> {

    Optional<MatchesEntity> findByFixtureId(Long fixtureId);

    List<MatchesEntity> findByStatusMatch(String statusMatch);

    List<MatchesEntity> findByStatusMatchAndOddsUploaded(String statusMatch, Boolean oddsUploaded);

    List<MatchesEntity> findByMatchDateBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);
}
