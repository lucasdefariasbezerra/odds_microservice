package com.lucasbezerra.oddsproject.api;

import com.lucasbezerra.oddsproject.exceptionHandler.RestInsertionHandler;
import com.lucasbezerra.oddsproject.model.Season;
import com.lucasbezerra.oddsproject.payloadManager.GenericPayloadGenerator;
import com.lucasbezerra.oddsproject.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/season")
public class SeasonApi {

    @Autowired
    SeasonService seasonService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> post(@RequestBody final Season season) {
        return handleRequest(season);
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> put(@RequestBody final Season season) {
        return handleRequest(season);
    }

    private ResponseEntity<?> handleRequest(Season season) {
        try {
            seasonService.save(season);
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
            return ResponseEntity.badRequest().body(null);
        return new ResponseEntity<>(seasonService.get(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getById(@PathVariable final Integer id) {
        try {
            return new ResponseEntity<>(seasonService.getById(id), HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            String content = "id " + id + " was not found";
            return new ResponseEntity<>(GenericPayloadGenerator
                    .getInstance()
                    .buildResponseMessage("message",content), HttpStatus.NOT_FOUND);
        }
    }
}
