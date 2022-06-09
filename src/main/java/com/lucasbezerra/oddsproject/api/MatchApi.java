package com.lucasbezerra.oddsproject.api;

import com.lucasbezerra.oddsproject.model.dto.UpdateMatchesDTO;
import com.lucasbezerra.oddsproject.payloadManager.GenericPayloadGenerator;
import com.lucasbezerra.oddsproject.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/api/match")
public class MatchApi {
    @Autowired
    private MatchService matchService;

    @CrossOrigin
    @PostMapping(value = "/upload", consumes = MULTIPART_FORM_DATA_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        boolean status = matchService.executeMatchUpload(file);
        String message = status ? "message successfully uploaded" : "error during upload try again later";
        return new ResponseEntity<>(GenericPayloadGenerator
                .getInstance()
                .buildResponseMessage("message",message), status ? HttpStatus.OK: HttpStatus.BAD_REQUEST);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> get(@RequestParam(required = false, name = "pageNum") Integer pageNum,
                                 @RequestParam(required = false, name = "pageSize") Integer pageSize,
                                 @RequestParam(required = false, name = "seasonId") Integer seasonId) {
        if (pageNum != null && pageSize != null) {
            return new ResponseEntity<>(matchService.getPaginated(pageNum, pageSize, seasonId), HttpStatus.OK);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @PutMapping(value = "/update-score", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateScores(@RequestBody List<UpdateMatchesDTO> matchesList) {
        try {
            matchService.updateMatchesScore(matchesList);
            return new ResponseEntity<>(GenericPayloadGenerator
                    .getInstance()
                    .buildResponseMessage("message","scores successfully updated"), HttpStatus.OK);
        } catch (Exception exception) {
            exception.printStackTrace();
            return new ResponseEntity<>(GenericPayloadGenerator
                    .getInstance()
                    .buildResponseMessage("message","error on update scores"), HttpStatus.BAD_REQUEST);
        }
    }

}
