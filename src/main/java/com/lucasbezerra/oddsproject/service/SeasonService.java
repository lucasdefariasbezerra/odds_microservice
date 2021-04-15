package com.lucasbezerra.oddsproject.service;

import com.lucasbezerra.oddsproject.exceptionHandler.RestInsertionHandler;
import com.lucasbezerra.oddsproject.model.*;
import com.lucasbezerra.oddsproject.model.dto.*;
import com.lucasbezerra.oddsproject.repository.*;
import com.lucasbezerra.oddsproject.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeasonService {
    private final static Logger logger = LoggerFactory.getLogger(SeasonService.class);

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private SeasonRepository seasonRepository;

    @Autowired
    private TournmentRepository tournmentRepository;

    @Autowired
    private SeasonGroupRepository seasonGroupRepository;

    @Autowired
    private MatchRepository matchRepository;

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
       return seasonRepository
               .findAll()
               .stream()
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

    public SeasonDTO getById(Integer id) throws EntityNotFoundException {
        Season season = seasonRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return new SeasonDTO(season);
    }

    public List<Tournment> getAllTournments() {
        return tournmentRepository.findAll();
    }

    public void addMatches(Integer seasonId, List<MatchesPayloadDTO> matches) throws RestInsertionHandler, ParseException {
       Season season = seasonRepository.findById(seasonId)
               .orElseThrow(()-> new RestInsertionHandler("season " + seasonId + " not found"));
       for (MatchesPayloadDTO currentMatchPayload : matches) {
           SeasonGroup group = seasonGroupRepository
                   .findOneByGroupKeyAndSeasonId(currentMatchPayload.getGroupKey(), seasonId)
                   .orElseGet(() -> insertNewSeasonGroup(currentMatchPayload, season));
           handleMatchInsertion(currentMatchPayload, group);
       }
    }

    private void handleMatchInsertion(MatchesPayloadDTO matchPayload, SeasonGroup group) throws RestInsertionHandler, ParseException {
        if (!isMatchAlreadyInserted(matchPayload.getTeamHome(), matchPayload.getTeamAway(), group.getId())) {
            Team teamHome = teamRepository
                    .findOneByName(matchPayload.getTeamHome())
                    .orElseThrow(() -> new RestInsertionHandler("team " + matchPayload.getTeamHome() + " not found"));

            Team teamAway = teamRepository
                    .findOneByName(matchPayload.getTeamAway())
                    .orElseThrow(() -> new RestInsertionHandler("team " + matchPayload.getTeamAway() + " not found"));

            long dateInMilli = DateUtils.convertDateStringToMilli(matchPayload.getDate());
            Match match = new Match(teamHome, teamAway, group, dateInMilli, matchPayload.getRound());
            matchRepository.save(match);
            logger.info("Match {} was inserted", match);
        }
    }

    private boolean isMatchAlreadyInserted(String homeName, String awayName, Integer groupId) {
        int count = matchRepository.countByTeamHomeNameAndTeamAwayNameAndSeasonGroupId(homeName, awayName, groupId);
        return count > 0;
    }

    private SeasonGroup insertNewSeasonGroup(MatchesPayloadDTO matchesPayloadDTO, Season seasonEntity) {
        SeasonGroup seasonGroup = new SeasonGroup();
        seasonGroup.setDesc(matchesPayloadDTO.getGroupDesc());
        seasonGroup.setGroupKey(matchesPayloadDTO.getGroupKey());
        seasonGroup.setSeason(seasonEntity);
        return seasonGroupRepository.save(seasonGroup);
    }
}
