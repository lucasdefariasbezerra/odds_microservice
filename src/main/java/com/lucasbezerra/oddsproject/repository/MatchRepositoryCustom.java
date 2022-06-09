package com.lucasbezerra.oddsproject.repository;

import com.lucasbezerra.oddsproject.model.dto.MatchesPayloadDTO;

import java.util.List;
import java.util.Map;

public interface MatchRepositoryCustom {

    List<MatchesPayloadDTO> searchMatches(Map<String,Object> searchFilter, int offset, int pageSize);

}
