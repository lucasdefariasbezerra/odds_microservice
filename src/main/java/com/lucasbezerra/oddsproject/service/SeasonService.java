package com.lucasbezerra.oddsproject.service;

import com.lucasbezerra.oddsproject.exceptionHandler.RestInsertionHandler;
import com.lucasbezerra.oddsproject.model.Season;
import com.lucasbezerra.oddsproject.model.Tournment;
import com.lucasbezerra.oddsproject.model.dto.*;
import com.lucasbezerra.oddsproject.repository.SeasonRepository;
import com.lucasbezerra.oddsproject.repository.TournmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeasonService {

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private TournmentRepository tournmentRepository;

    public void save(SeasonRequestDTO seasonPayload) throws RestInsertionHandler {
        Season season = new Season(seasonPayload);
        try {
            season = seasonRepository.save(season);
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

    public PageDTO<SeasonDTO, Page<Season>> getPaginated(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Season> page = seasonRepository.findAll(pageable);
        PageDTO<SeasonDTO, Page<Season>> pageDTO = new SeasonPageDTO();
        pageDTO.mapPageToDTO(page);
        return pageDTO;
    }

    public Season getById(Integer id) throws EntityNotFoundException {
        return seasonRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<Tournment> getAllTournments() {
        return tournmentRepository.findAll();
    }
}
