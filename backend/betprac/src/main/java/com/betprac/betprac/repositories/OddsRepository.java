package com.betprac.betprac.repositories;

import com.betprac.betprac.entities.OddsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OddsRepository extends JpaRepository<OddsEntity, Long> {
}
