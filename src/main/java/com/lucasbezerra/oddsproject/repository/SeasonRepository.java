package com.lucasbezerra.oddsproject.repository;

import com.lucasbezerra.oddsproject.model.Season;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasonRepository extends JpaRepository<Season,Integer> {
}
