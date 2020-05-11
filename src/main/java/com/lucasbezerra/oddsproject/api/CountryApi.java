package com.lucasbezerra.oddsproject.api;

import com.lucasbezerra.oddsproject.exceptionHandler.RestInsertionHandler;
import com.lucasbezerra.oddsproject.model.Country;
import com.lucasbezerra.oddsproject.payloadManager.GenericPayloadGenerator;
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

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> post(@RequestBody final Country country) {
        return handleRquest(country);
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> put(@RequestBody final Country country) {
        return handleRquest(country);
    }

    private ResponseEntity<?> handleRquest(Country country) {
        try {
            countryService.save(country);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch(RestInsertionHandler ex) {
            return new ResponseEntity<>(GenericPayloadGenerator
                    .getInstance()
                    .buildResponseMessage("message",ex.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> get(@RequestParam(required = false, name = "pageNum") Integer pageNum,
                                             @RequestParam(required = false, name = "pageSize") Integer pageSize) {
        if (pageNum != null && pageSize != null)
            return new ResponseEntity<>(countryService.getPaginatedCountries(pageNum, pageSize), HttpStatus.OK);
        return new ResponseEntity<>(countryService.get(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getById(@PathVariable final Integer id) {
        try {
            return new ResponseEntity<>(countryService.getById(id), HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            String content = "id " + id + " was not found";
            return new ResponseEntity<>(GenericPayloadGenerator
                    .getInstance()
                    .buildResponseMessage("message",content), HttpStatus.NOT_FOUND);
        }
    }
}
