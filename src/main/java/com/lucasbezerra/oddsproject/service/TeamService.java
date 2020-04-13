package com.lucasbezerra.oddsproject.service;

import com.lucasbezerra.oddsproject.exceptionHandler.RestInsertionHandler;
import com.lucasbezerra.oddsproject.model.Team;
import com.lucasbezerra.oddsproject.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TeamService {
    @Autowired
    private TeamRepository teamRepository;

    public void save(Team team) throws RestInsertionHandler {
        try {
            teamRepository.save(team);
        } catch (Exception ex) {
            throw new RestInsertionHandler(team.getName() + " is already inserted");
        }
    }

    public List<Team> get(Integer pageNum, Integer pageSize) {
        if (pageNum != null && pageSize != null) {
            Pageable pageable = PageRequest.of(pageNum, pageSize);
            return teamRepository.findAll(pageable).toList();
        }

        return teamRepository.findAll();
    }

    public Team getById(Integer id) {
        return teamRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}