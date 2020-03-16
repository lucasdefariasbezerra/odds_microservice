package com.lucasbezerra.oddsproject.service;

import com.lucasbezerra.oddsproject.exceptionHandler.CountryExceptionHandler;
import com.lucasbezerra.oddsproject.model.Country;
import com.lucasbezerra.oddsproject.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepository countryRepository;

    public void saveCountry(Country country) throws CountryExceptionHandler {
        try {
            countryRepository.save(country);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new CountryExceptionHandler(country.getName() + " is already inserted");
        }
    }

    public List<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    public List<Country> getAllPaginatedCountries(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return countryRepository.findAll(pageable).toList();
    }

    public Country getCountryById(Integer id) throws EntityNotFoundException {
        return countryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
