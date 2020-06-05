package com.lucasbezerra.oddsproject.model.dto;

import com.lucasbezerra.oddsproject.model.Country;
import com.lucasbezerra.oddsproject.model.Team;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class TeamPageDTO extends PageDTO<TeamDTO, Page<Team>>  {

    @Override
    public void mapPageToDTO(Page<Team> paginator) {
        List<TeamDTO> items = paginator.get()
                .map(Team::toDTO)
                .collect(Collectors.toList());
        this.setItems(items);
        this.setTotal(paginator.getTotalPages());
    }
}
