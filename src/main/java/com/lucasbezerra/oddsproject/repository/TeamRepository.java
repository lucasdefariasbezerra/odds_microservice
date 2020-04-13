package com.lucasbezerra.oddsproject.repository;

import com.lucasbezerra.oddsproject.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Integer> {
}
