package com.lucasbezerra.oddsproject.api;

import com.lucasbezerra.oddsproject.exceptionHandler.RestInsertionHandler;
import com.lucasbezerra.oddsproject.model.dto.MatchesPayloadDTO;
import com.lucasbezerra.oddsproject.model.dto.SeasonRequestDTO;
import com.lucasbezerra.oddsproject.payloadManager.GenericPayloadGenerator;
import com.lucasbezerra.oddsproject.service.SeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

import java.text.ParseException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/season")
public class SeasonApi {


    @Autowired
    private SeasonService seasonService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> post(@RequestBody final SeasonRequestDTO season) {
        return handleRequest(season);
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> put(@RequestBody final SeasonRequestDTO season) {
        return handleRequest(season);
    }

    private ResponseEntity<?> handleRequest(SeasonRequestDTO season) {
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
            return new ResponseEntity<>(seasonService.getPaginated(pageNum, pageSize), HttpStatus.OK);
        return new ResponseEntity<>(seasonService.get(), HttpStatus.OK);
    }

    @GetMapping(value="/tournment" ,produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getTournment() {
        return new ResponseEntity<>(seasonService.getAllTournments(), HttpStatus.OK);
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

    @PostMapping(value = "/{id}/matches", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addMatches(@PathVariable final Integer id,
                                        @RequestBody List<MatchesPayloadDTO> matchesPayloadDTO) {
        try {
            seasonService.addMatches(id, matchesPayloadDTO);
            return ResponseEntity.ok(null);
        } catch (RestInsertionHandler | ParseException ex) {
           return ResponseEntity.badRequest().body(GenericPayloadGenerator
                   .getInstance()
                   .buildResponseMessage("error", ex.getMessage()));
        }
    }
}
