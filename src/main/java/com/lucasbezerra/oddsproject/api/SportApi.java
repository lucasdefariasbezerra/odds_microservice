package com.lucasbezerra.oddsproject.api;

import com.lucasbezerra.oddsproject.service.SportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/sport")
public class SportApi {

    @Autowired
    private SportService sportService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> get() {
        return new ResponseEntity<>(sportService.findAllSports(), HttpStatus.OK);
    }

}
