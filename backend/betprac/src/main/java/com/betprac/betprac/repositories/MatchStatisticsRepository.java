package com.betprac.betprac.repositories;

import com.betprac.betprac.entities.MatchStatisticsEntity;
import com.betprac.betprac.entities.MatchesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatchStatisticsRepository extends JpaRepository<MatchStatisticsEntity, Long> {

    Optional<MatchStatisticsEntity> findByMatch(MatchesEntity match);
}
