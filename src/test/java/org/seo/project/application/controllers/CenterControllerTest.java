package org.seo.project.application.controllers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.seo.project.application.models.dto.CenterDTO;
import org.seo.project.application.models.entity.Center;
import org.seo.project.application.models.response.CenterResponse;
import org.seo.project.application.models.response.ResponseObject;
import org.seo.project.application.repositories.CenterRepository;
import org.seo.project.application.service.CenterService;
import org.seo.project.application.service.implement.CenterServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CenterControllerTest {
    @InjectMocks
    CenterController centerController;
    @Mock
    CenterService centerService;
    @BeforeEach
    void setUp() {
    }

    @Test
    void getCenter() {
        when(centerService.getCenter(anyString())).thenReturn(Collections.emptyList());
        ResponseEntity<ResponseObject> response = centerController.getCenter(anyString());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("OK", response.getBody().getStatus());
        assertEquals("Get center successfully", response.getBody().getMsg());
        assertEquals(Collections.emptyList(), response.getBody().getData());
    }

    @Test
    void insertCenter() {
        CenterDTO centerDTO = mock(CenterDTO.class);
        Center center = mock(Center.class);
        when(centerService.insertCenter(any(Center.class))).thenReturn(center);
        ResponseEntity<ResponseObject> response = centerController.insertCenter(centerDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("OK", response.getBody().getStatus());
        assertEquals("Insert center successfully", response.getBody().getMsg());
        assertEquals(center, response.getBody().getData());
    }

    @Test
    void putCenter() {
        CenterDTO centerDTO = mock(CenterDTO.class);
        Center center = mock(Center.class);
        when(centerService.editCenter(any(Center.class))).thenReturn(center);
        ResponseEntity<ResponseObject> response = centerController.putCenter(centerDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("OK", response.getBody().getStatus());
        assertEquals("Upsert center successfully", response.getBody().getMsg());
        assertEquals(center, response.getBody().getData());
    }

    @Test
    void deleteCenter() {
        ResponseEntity<ResponseObject> response = centerController.deleteCenter(anyString());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("OK", response.getBody().getStatus());
        assertEquals("Delete center successfully", response.getBody().getMsg());
        assertEquals("", response.getBody().getData());
        verify(centerService, times(1)).deleteCenter(anyString());
    }

    @Test
    void getAllFresherOfCenter() {
        CenterResponse centerResponse = mock(CenterResponse.class);
        when(centerService.getAllFresherOfCenter(anyString())).thenReturn(centerResponse);

        ResponseEntity<ResponseObject> response = centerController.getAllFresherOfCenter(anyString());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("OK", response.getBody().getStatus());
        assertEquals("Get all fresher of center successfully", response.getBody().getMsg());
        assertEquals(centerResponse,response.getBody().getData());
    }
}