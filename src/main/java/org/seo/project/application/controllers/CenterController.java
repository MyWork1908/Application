package org.seo.project.application.controllers;

import org.seo.project.application.models.dto.CenterDTO;
import org.seo.project.application.models.dto.mapper.CenterMapper;
import org.seo.project.application.models.entity.Center;
import org.seo.project.application.models.response.ResponseObject;
import org.seo.project.application.service.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/center")
public class CenterController {
    @Autowired
    CenterService centerService;
    @GetMapping("/get")
    public ResponseEntity<ResponseObject> getCenter(
            @RequestParam(required = false, defaultValue = "") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(
          new ResponseObject("OK","Get center successfully",
                  centerService.getCenter(id))
        );
    }
    @PostMapping("/add")
    public ResponseEntity<ResponseObject> insertCenter(@RequestBody CenterDTO centerDTO) {
        CenterMapper centerMapper = new CenterMapper();
        Center center = centerMapper.toCenter(centerDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
          new ResponseObject("OK","Insert center successfully",
                  centerService.insertCenter(center))
        );
    }
    @PutMapping("/put")
    public ResponseEntity<ResponseObject> putCenter(@RequestBody CenterDTO centerDTO) {
        CenterMapper centerMapper = new CenterMapper();
        Center center = centerMapper.toCenter(centerDTO);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK","Upsert center successfully",
                        centerService.editCenter(center))
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseObject> deleteCenter(@PathVariable String id) {
        centerService.deleteCenter(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK","Delete center successfully","")
        );
    }

    @GetMapping("/fresher/{id}")
    public ResponseEntity<ResponseObject> getAllFresherOfCenter(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK","Get all fresher of center successfully",
                        centerService.getAllFresherOfCenter(id))
        );
    }
}
