package com.lucasbezerra.oddsproject.service;

import com.lucasbezerra.oddsproject.exceptionHandler.RestInsertionHandler;
import com.lucasbezerra.oddsproject.model.Team;
import com.lucasbezerra.oddsproject.model.dto.PageDTO;
import com.lucasbezerra.oddsproject.model.dto.TeamDTO;
import com.lucasbezerra.oddsproject.model.dto.TeamPageDTO;
import com.lucasbezerra.oddsproject.model.dto.TeamUploadDTO;
import com.lucasbezerra.oddsproject.repository.TeamRepository;
import com.lucasbezerra.oddsproject.utils.CsvUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
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


    public boolean executeTeamUpload(MultipartFile file) {
        try {
            List<TeamUploadDTO> teams = CsvUtils.read(TeamUploadDTO.class, file.getInputStream());
            for (TeamUploadDTO teamUploadDTO : teams) {
                if (teamRepository.countByName(teamUploadDTO.getName()) == 0) {
                    teamRepository.save(new Team(teamUploadDTO));
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    public List<Team> get() {
        return teamRepository.findAll();
    }

    public PageDTO getPaginated(Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Team> page = teamRepository.findAll(pageable);
        PageDTO<TeamDTO, Page<Team>> pageDTO = new TeamPageDTO();
        pageDTO.mapPageToDTO(page);
        return pageDTO;
    }


    public Team getById(Integer id) {
        return teamRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
