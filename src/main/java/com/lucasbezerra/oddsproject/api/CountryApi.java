package com.lucasbezerra.oddsproject.api;

import com.lucasbezerra.oddsproject.model.Country;
import com.lucasbezerra.oddsproject.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/country")
public class CountryApi {

    @Autowired
    CountryService countryService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Country>> get(@RequestParam(required = false, name = "pageNum") Integer pageNum,
                                             @RequestParam(required = false, name = "pageSize") Integer pageSize) {
        List<Country> countryBody;
        if (pageNum != null && pageSize != null)
            countryBody = countryService.getAllPaginatedCountries(pageNum, pageSize);
        else
            countryBody = countryService.getAllCountries();
        return new ResponseEntity<>(countryBody, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Country> getById(@PathVariable final Integer id) {
        try {
            return new ResponseEntity<>(countryService.getCountryById(id), HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }
}
