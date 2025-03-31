package com.betprac.betprac.FootballAPI.repositories;

import com.betprac.betprac.FootballAPI.entities.OddsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OddsRepository extends JpaRepository<OddsEntity, Long> {
    List<OddsEntity> findByMatchId(Long matchId);
}
