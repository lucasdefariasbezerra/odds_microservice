package com.lucasbezerra.oddsproject.model.dto;

import com.lucasbezerra.oddsproject.model.Match;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MatchPageDTO extends PageDTO<MatchesPayloadDTO, Page<Match>> {
    @Override
    public void mapPageToDTO(Page<Match> paginator) {
        List<MatchesPayloadDTO> items = paginator
                .get()
                .map(Match::toDTO)
                .collect(Collectors.toList());
        this.setItems(items);
        this.setTotal(paginator.getTotalElements());

    }

    public void mapPageToDTO(List<MatchesPayloadDTO> items, int totalItems, int pageSize) {
        this.setItems(items);
        this.setTotal(totalItems);
    }

}
