package com.lucasbezerra.oddsproject.service;

import com.lucasbezerra.oddsproject.model.Sport;
import com.lucasbezerra.oddsproject.repository.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SportService {
    @Autowired
    private SportRepository sportRepository;

    public List<Sport> findAllSports() {
        return sportRepository.findAll();
    }
}
