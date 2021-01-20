package com.lucasbezerra.oddsproject.api;

import com.lucasbezerra.oddsproject.exceptionHandler.RestInsertionHandler;
import com.lucasbezerra.oddsproject.model.Team;
import com.lucasbezerra.oddsproject.model.dto.TeamUploadDTO;
import com.lucasbezerra.oddsproject.payloadManager.GenericPayloadGenerator;
import com.lucasbezerra.oddsproject.service.TeamService;
import com.lucasbezerra.oddsproject.service.utils.CsvUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/api/team")
public class TeamApi {

    @Autowired
    TeamService teamService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> post(@RequestBody final Team team) {
        return handleRequest(team);
    }

    @CrossOrigin
    @PostMapping(value = "/upload", consumes = MULTIPART_FORM_DATA_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        boolean status = teamService.executeTeamUpload(file);
        String message = status ? "message successfully uploaded" : "error during upload try again later";
        return new ResponseEntity<>(GenericPayloadGenerator
                    .getInstance()
                    .buildResponseMessage("message",message), status ? HttpStatus.OK: HttpStatus.BAD_REQUEST);
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
    public ResponseEntity<?> get(@RequestParam(required = false, name = "pageNum") Integer pageNum,
                                             @RequestParam(required = false, name = "pageSize") Integer pageSize) {
        if (pageNum != null && pageSize != null)
            return new ResponseEntity<>(teamService.getPaginated(pageNum, pageSize), HttpStatus.OK);
        return new ResponseEntity<>(teamService.get(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getById(@PathVariable final Integer id) {
        try {
            return new ResponseEntity<>(teamService.getById(id), HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            String content = "id " + id + " was not found";
            return new ResponseEntity<>(GenericPayloadGenerator
                    .getInstance()
                    .buildResponseMessage("message",content), HttpStatus.NOT_FOUND);
        }
    }
}
