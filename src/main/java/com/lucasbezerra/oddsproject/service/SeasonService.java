package com.lucasbezerra.oddsproject.service;

import com.lucasbezerra.oddsproject.exceptionHandler.RestInsertionHandler;
import com.lucasbezerra.oddsproject.model.Season;
import com.lucasbezerra.oddsproject.model.dto.SeasonDTO;
import com.lucasbezerra.oddsproject.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeasonService {

    @Autowired
    private SeasonRepository seasonRepository;

    public void save(Season season) throws RestInsertionHandler {
        try {
            seasonRepository.save(season);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RestInsertionHandler(season.getNameRight() + " is already inserted");
        }

    }

    public List<SeasonDTO> get() {
       return seasonRepository.findAll().stream()
               .map(SeasonDTO::new)
               .collect(Collectors.toList());
    }

    public Season getById(Integer id) throws EntityNotFoundException {
        return seasonRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
