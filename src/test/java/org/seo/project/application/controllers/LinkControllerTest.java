package org.seo.project.application.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.seo.project.application.models.dto.ScoreDTO;
import org.seo.project.application.models.dto.mapper.ScoreMapper;
import org.seo.project.application.models.entity.Score;
import org.seo.project.application.models.response.CenterResponse;
import org.seo.project.application.models.response.ResponseObject;
import org.seo.project.application.models.response.ScoreResponse;
import org.seo.project.application.service.LinkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LinkControllerTest {
    @InjectMocks
    LinkController linkController;
    @Mock
    LinkService linkService;
    @Mock
    ScoreMapper scoreMapper;

    @Test
    void addFresherToCenter() {
        CenterResponse centerResponse = mock(CenterResponse.class);
        when(linkService.addFresherToCenter(anyString(),anyString())).thenReturn(centerResponse);
        ResponseEntity<ResponseObject> response = linkController.addFresherToCenter(anyString(),anyString());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("OK", response.getBody().getStatus());
        assertEquals("Add fresher to center successfully", response.getBody().getMsg());
        assertEquals(centerResponse,response.getBody().getData());
    }

    @Test
    void addScore() {
        ScoreDTO scoreDTO = mock(ScoreDTO.class);
        ScoreResponse scoreResponse = mock(ScoreResponse.class);
        when(linkService.addScore(any(Score.class),any(),any())).thenReturn(scoreResponse);
        ResponseEntity<ResponseObject> response = linkController.addScore(scoreDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("OK", response.getBody().getStatus());
        assertEquals("Add score successfully", response.getBody().getMsg());
        assertEquals(scoreResponse,response.getBody().getData());
    }
}