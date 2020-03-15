package com.lucasbezerra.oddsproject.repository;

import com.lucasbezerra.oddsproject.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
}
