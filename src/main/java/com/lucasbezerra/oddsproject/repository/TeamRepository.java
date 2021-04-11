package com.lucasbezerra.oddsproject.repository;

import com.lucasbezerra.oddsproject.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
    int countByName(String name);
    Optional<Team> findOneByName(String name);
}