package com.lucasbezerra.oddsproject.repository;

import com.lucasbezerra.oddsproject.model.SeasonGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeasonGroupRepository extends JpaRepository<SeasonGroup, Integer> {
    Optional<SeasonGroup> findOneByGroupKeyAndSeasonId(String groupKey, Integer seasonId);
}
