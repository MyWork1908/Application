package org.seo.project.application.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.seo.project.application.models.dto.CenterDTO;
import org.seo.project.application.models.dto.SubjectDTO;
import org.seo.project.application.models.entity.Center;
import org.seo.project.application.models.entity.Subject;
import org.seo.project.application.models.response.CenterResponse;
import org.seo.project.application.models.response.ResponseObject;
import org.seo.project.application.models.response.ScoreResponse;
import org.seo.project.application.service.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class SubjectControllerTest {
    @InjectMocks
    SubjectController subjectController;
    @Mock
    SubjectService subjectService;

    @Test
    void getSubject() {
        when(subjectService.getSubject(anyString())).thenReturn(Collections.emptyList());
        ResponseEntity<ResponseObject> response = subjectController.getSubject(anyString());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("OK", response.getBody().getStatus());
        assertEquals("Get subject successfully", response.getBody().getMsg());
        assertEquals(Collections.emptyList(), response.getBody().getData());
    }

    @Test
    void insertSubject() {
        SubjectDTO subjectDTO = mock(SubjectDTO.class);
        Subject subject = mock(Subject.class);
        when(subjectService.insertSubject(any(Subject.class))).thenReturn(subject);
        ResponseEntity<ResponseObject> response = subjectController.insertSubject(subjectDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("OK", response.getBody().getStatus());
        assertEquals("Insert subject successfully", response.getBody().getMsg());
        assertEquals(subject, response.getBody().getData());
    }

    @Test
    void editSubject() {
        SubjectDTO subjectDTO = mock(SubjectDTO.class);
        Subject subject = mock(Subject.class);
        when(subjectService.editSubject(any(Subject.class))).thenReturn(subject);
        ResponseEntity<ResponseObject> response = subjectController.putSubject(subjectDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("OK", response.getBody().getStatus());
        assertEquals("Upsert subject successfully", response.getBody().getMsg());
        assertEquals(subject, response.getBody().getData());
    }

    @Test
    void deleteSubject() {
        ResponseEntity<ResponseObject> response = subjectController.deleteSubject(anyString());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("OK", response.getBody().getStatus());
        assertEquals("Delete subject successfully", response.getBody().getMsg());
        assertEquals("", response.getBody().getData());
        verify(subjectService, times(1)).deleteSubject(anyString());
    }

    @Test
    void getScore() {
        when(subjectService.getScore(anyString())).thenReturn(Collections.emptyList());

        ResponseEntity<ResponseObject> response = subjectController.getScore(anyString());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("OK", response.getBody().getStatus());
        assertEquals("Get score of subject successfully", response.getBody().getMsg());
        assertEquals(Collections.emptyList(),response.getBody().getData());
    }
}