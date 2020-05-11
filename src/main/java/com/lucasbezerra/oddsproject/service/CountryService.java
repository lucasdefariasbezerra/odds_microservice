package com.lucasbezerra.oddsproject.service;

import com.lucasbezerra.oddsproject.exceptionHandler.RestInsertionHandler;
import com.lucasbezerra.oddsproject.model.Country;
import com.lucasbezerra.oddsproject.model.dto.CountryDTO;
import com.lucasbezerra.oddsproject.model.dto.PageDTO;
import com.lucasbezerra.oddsproject.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public void save(Country country) throws RestInsertionHandler {
        try {
            countryRepository.save(country);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RestInsertionHandler(country.getName() + " is already inserted");
        }
    }

    public List<Country> get() {
        return countryRepository.findAll();
    }

    public PageDTO getPaginatedCountries(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        Page<Country> page = countryRepository.findAll(pageable);
        List<CountryDTO> items = page.get()
                .map(Country::toDTO)
                .collect(Collectors.toList());
        return new PageDTO(page.getTotalPages(), items);
    }

    public Country getById(Integer id) throws EntityNotFoundException {
        return countryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
