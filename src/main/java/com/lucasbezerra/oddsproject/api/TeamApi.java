package com.lucasbezerra.oddsproject.api;

import com.lucasbezerra.oddsproject.exceptionHandler.RestInsertionHandler;
import com.lucasbezerra.oddsproject.model.Team;
import com.lucasbezerra.oddsproject.payloadManager.GenericPayloadGenerator;
import com.lucasbezerra.oddsproject.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/team")
public class TeamApi {

    @Autowired
    TeamService teamService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> post(@RequestBody final Team team) {
        return handleRequest(team);
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> put(@RequestBody final Team team) {
        return handleRequest(team);
    }

    private ResponseEntity<?> handleRequest(Team team) {
        try {
            teamService.save(team);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch(RestInsertionHandler ex) {
            return new ResponseEntity<>(GenericPayloadGenerator
                    .getInstance()
                    .buildResponseMessage("message",ex.getMessage()),
                    HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Team>> get(@RequestParam(required = false, name = "pageNum") Integer pageNum,
                                             @RequestParam(required = false, name = "pageSize") Integer pageSize) {
        List<Team> countryBody = teamService.get(pageNum, pageSize);
        return new ResponseEntity<>(countryBody, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Team> getById(@PathVariable final Integer id) {
        try {
            return new ResponseEntity<>(teamService.getById(id), HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
