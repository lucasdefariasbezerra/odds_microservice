package com.lucasbezerra.oddsproject.repository;

import com.lucasbezerra.oddsproject.model.Sport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportRepository extends JpaRepository<Sport, Integer> {
}
