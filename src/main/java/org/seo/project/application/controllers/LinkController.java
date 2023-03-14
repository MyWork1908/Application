package org.seo.project.application.controllers;

import org.seo.project.application.models.dto.ScoreDTO;
import org.seo.project.application.models.dto.mapper.ScoreMapper;
import org.seo.project.application.models.entity.Score;
import org.seo.project.application.models.response.ResponseObject;
import org.seo.project.application.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/link")
public class LinkController {
    @Autowired
    LinkService linkService;
    @PostMapping("/{id}")
    public ResponseEntity<ResponseObject> addFresherToCenter(@PathVariable(name = "id") String centerId,
                                                             @RequestParam String fresherId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK","Add fresher to center successfully",
                        linkService.addFresherToCenter(fresherId, centerId)));
    }
    @PostMapping("/score")
    public ResponseEntity<ResponseObject> addScore(@RequestBody ScoreDTO scoreDTO) {
        ScoreMapper scoreMapper = new ScoreMapper();
        Score score = scoreMapper.toScore(scoreDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK","Add score successfully",
                        linkService.addScore(score,scoreDTO.getFresherId(),scoreDTO.getSubjectId()))
        );
    }
}
