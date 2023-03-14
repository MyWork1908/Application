package org.seo.project.application.controllers;

import org.seo.project.application.models.dto.FresherDTO;
import org.seo.project.application.models.dto.mapper.FresherMapper;
import org.seo.project.application.models.entity.Fresher;
import org.seo.project.application.models.response.ResponseObject;
import org.seo.project.application.service.FresherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fresher")
public class FresherController {
    @Autowired
    FresherService fresherService;
    @GetMapping("/get")
    public ResponseEntity<ResponseObject> getFresher(
            @RequestParam(required = false, defaultValue = "") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(
          new ResponseObject("OK","Get fresher successfully",
                  fresherService.getFresher(id))
        );
    }

    @PostMapping("/insert")
    public ResponseEntity<ResponseObject> insertFresher(@RequestBody FresherDTO fresherDTO) {
        FresherMapper fresherMapper = new FresherMapper();
        Fresher fresher = fresherMapper.toFresher(fresherDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK","Insert fresher successfully",
                        fresherService.insertFresher(fresher))
        );
    }

    @PutMapping("/put")
    public ResponseEntity<ResponseObject> putFresher(@RequestBody FresherDTO fresherDTO) {
        FresherMapper fresherMapper = new FresherMapper();
        Fresher fresher = fresherMapper.toFresher(fresherDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK","Upsert fresher successfully",
                        fresherService.editFresher(fresher))
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseObject> deleteFresher(@PathVariable String id) {
        fresherService.deleteFresher(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK","Delete fresher successfully","")
        );
    }
    @GetMapping("/score")
    public ResponseEntity<ResponseObject> getScore(@RequestParam(required = false, defaultValue = "") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK","Get score of fresher successfully",
                        fresherService.getScore(id))
        );
    }
    @GetMapping("/search")
    public ResponseEntity<ResponseObject> searchFresher(@RequestParam(name = "name", required = false) String name,
                                                        @RequestParam(name = "email", required = false) String email,
                                                        @RequestParam(name = "lp", required = false) String lp) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK","Search fresher successfully",
                        fresherService.searchFresher(name,email,lp))
        );
    }
    @GetMapping("/statistical")
    public ResponseEntity<ResponseObject> statisticalFresher(@RequestParam(required = false) Double equal,
                                                             @RequestParam(required = false) Double up,
                                                             @RequestParam(required = false) Double down) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK","statistical fresher by score successfully",
                        fresherService.statisticalFresherWithScore(equal, up, down))
        );
    }
}
