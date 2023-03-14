package org.seo.project.application.controllers;

import org.seo.project.application.models.dto.SubjectDTO;
import org.seo.project.application.models.dto.mapper.SubjectMapper;
import org.seo.project.application.models.entity.Subject;
import org.seo.project.application.models.response.ResponseObject;
import org.seo.project.application.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/subject")
public class SubjectController {
    @Autowired
    SubjectService subjectService;
    @GetMapping("/get")
    public ResponseEntity<ResponseObject> getSubject(@RequestParam String id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK","Get subject successfully",
                        subjectService.getSubject(id))
        );
    }
    @PostMapping("/add")
    public ResponseEntity<ResponseObject> insertSubject(
            @RequestBody SubjectDTO subjectDTO) {
        SubjectMapper subjectMapper = new SubjectMapper();
        Subject subject = subjectMapper.toSubject(subjectDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK","Insert subject successfully",
                        subjectService.insertSubject(subject))
        );
    }
    @PutMapping("/put")
    public ResponseEntity<ResponseObject> putSubject(
            @RequestBody SubjectDTO subjectDTO) {
        SubjectMapper subjectMapper = new SubjectMapper();
        Subject subject = subjectMapper.toSubject(subjectDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK","Upsert subject successfully",
                        subjectService.editSubject(subject))
        );
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseObject> deleteSubject(@PathVariable String id) {
        subjectService.deleteSubject(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK","Delete subject successfully", "")
        );
    }
    @GetMapping("/score")
    public ResponseEntity<ResponseObject> getScore(
            @RequestParam(required = false, defaultValue = "") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK","Get score of subject successfully",
                        subjectService.getScore(id))
        );
    }
}
