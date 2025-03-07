package com.betprac.betprac.repositories;

import com.betprac.betprac.entities.MatchesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatchesRepository extends JpaRepository<MatchesEntity, Long> {
    Optional<MatchesEntity> findByFixtureId(Long fixtureId);
}
