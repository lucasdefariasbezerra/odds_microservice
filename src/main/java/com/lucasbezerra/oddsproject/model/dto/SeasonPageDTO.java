package com.lucasbezerra.oddsproject.model.dto;

import com.lucasbezerra.oddsproject.model.Season;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class SeasonPageDTO extends PageDTO<SeasonDTO, Page<Season>> {
    @Override
    public void mapPageToDTO(Page<Season> paginator) {
        List<SeasonDTO> items = paginator.get()
                .map(Season::toDTO)
                .collect(Collectors.toList());
        this.setItems(items);
        this.setTotal(paginator.getTotalElements());
    }
}
