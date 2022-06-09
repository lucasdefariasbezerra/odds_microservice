package com.lucasbezerra.oddsproject.service;

import com.lucasbezerra.oddsproject.model.Match;
import com.lucasbezerra.oddsproject.model.dto.MatchPageDTO;
import com.lucasbezerra.oddsproject.model.dto.MatchesPayloadDTO;
import com.lucasbezerra.oddsproject.model.dto.PageDTO;
import com.lucasbezerra.oddsproject.model.dto.UpdateMatchesDTO;
import com.lucasbezerra.oddsproject.repository.MatchRepository;
import com.lucasbezerra.oddsproject.utils.CsvUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MatchService {

    @Autowired
    private SeasonService seasonService;

    @Autowired
    private MatchRepository matchRepository;

    public boolean executeMatchUpload(MultipartFile file) {
        try {
            List<MatchesPayloadDTO> matchesDTO = CsvUtils.read(MatchesPayloadDTO.class, file.getInputStream());
            Map<Integer, List<MatchesPayloadDTO>> seasonMatches = getMappedList(matchesDTO);
            for (Map.Entry<Integer, List<MatchesPayloadDTO>> kv : seasonMatches.entrySet())
                seasonService.addMatches(kv.getKey(), kv.getValue());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private Map<Integer, List<MatchesPayloadDTO>> getMappedList(List<MatchesPayloadDTO> matches) {
        Map<Integer, List<MatchesPayloadDTO>> mappedMatches = new HashMap<>();
        for (MatchesPayloadDTO currentMatch : matches) {
            List<MatchesPayloadDTO> mappedList = mappedMatches.get(currentMatch.getSeasonId());
            if (mappedList != null) {
                mappedList.add(currentMatch);
            } else {
                mappedList = new ArrayList<>();
                mappedList.add(currentMatch);
                mappedMatches.put(currentMatch.getSeasonId(), mappedList);
            }
        }
        return mappedMatches;
    }

    public PageDTO getPaginated(int pageNum, int pageSize, int seasonId) {
        int offset = pageNum * pageSize;
        int totalItems = matchRepository.countBySeasonGroupSeasonId(seasonId);

        Map<String, Object> paramList = new HashMap<>();
        paramList.put("ss.id", seasonId);

        List<MatchesPayloadDTO> items = matchRepository.searchMatches(paramList, offset, pageSize);

        PageDTO<MatchesPayloadDTO, Page<Match>> pageDTO = new MatchPageDTO();
        pageDTO.mapPageToDTO(items, totalItems, pageSize);
        return pageDTO;
    }

    public void updateMatchesScore (List<UpdateMatchesDTO> matchesList) throws Exception {
        List<Match> toUpdate = new ArrayList<>();
        for (UpdateMatchesDTO current : matchesList) {
            matchRepository.findById(current.getId()).ifPresent(entity -> {
                entity.setScoreHome(current.getScoreHome());
                entity.setScoreAway(current.getScoreAway());
                entity.setProcessed(1);
                toUpdate.add(entity);
            });
        }
        matchRepository.saveAll(toUpdate);
    }
}
