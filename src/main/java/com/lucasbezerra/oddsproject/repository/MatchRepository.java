package com.lucasbezerra.oddsproject.repository;

import com.lucasbezerra.oddsproject.model.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Integer>, MatchRepositoryCustom {
    int countByTeamHomeNameAndTeamAwayNameAndSeasonGroupId(String homeName, String awayName, Integer groupId);
    int countBySeasonGroupSeasonId(Integer seasonId);
}