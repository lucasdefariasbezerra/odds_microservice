package com.lucasbezerra.oddsproject.model.dto;

import com.lucasbezerra.oddsproject.model.Country;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class CountryPageDTO extends PageDTO<CountryDTO, Page<Country>> {

    @Override
    public void mapPageToDTO(Page<Country> paginator) {
        List<CountryDTO> items = paginator.get()
                .map(Country::toDTO)
                .collect(Collectors.toList());
        this.setItems(items);
        this.setTotal(paginator.getTotalPages());
    }
}
