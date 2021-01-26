package com.lucasbezerra.oddsproject.repository;

import com.lucasbezerra.oddsproject.model.Tournment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TournmentRepository extends JpaRepository<Tournment, Integer> {
}
