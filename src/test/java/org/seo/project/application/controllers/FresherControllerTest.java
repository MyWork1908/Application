package org.seo.project.application.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.seo.project.application.models.dto.FresherDTO;
import org.seo.project.application.models.entity.Fresher;
import org.seo.project.application.models.response.ResponseObject;
import org.seo.project.application.service.FresherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class FresherControllerTest {
    @InjectMocks
    FresherController fresherController;
    @Mock
    FresherService fresherService;

    @Test
    void getFresher() {
        when(fresherService.getFresher(anyString())).thenReturn(Collections.emptyList());
        ResponseEntity<ResponseObject> response = fresherController.getFresher(anyString());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("OK", response.getBody().getStatus());
        assertEquals("Get fresher successfully", response.getBody().getMsg());
        assertEquals(Collections.emptyList(), response.getBody().getData());
    }

    @Test
    void insertFresher() {
        FresherDTO fresherDTO = mock(FresherDTO.class);
        Fresher fresher = mock(Fresher.class);
        when(fresherService.insertFresher(any(Fresher.class))).thenReturn(fresher);
        ResponseEntity<ResponseObject> response = fresherController.insertFresher(fresherDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("OK", response.getBody().getStatus());
        assertEquals("Insert fresher successfully", response.getBody().getMsg());
        assertEquals(fresher, response.getBody().getData());
    }

    @Test
    void putFresher() {
        FresherDTO fresherDTO = mock(FresherDTO.class);
        Fresher fresher = mock(Fresher.class);
        when(fresherService.editFresher(any(Fresher.class))).thenReturn(fresher);
        ResponseEntity<ResponseObject> response = fresherController.putFresher(fresherDTO);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("OK", response.getBody().getStatus());
        assertEquals("Upsert fresher successfully", response.getBody().getMsg());
        assertEquals(fresher, response.getBody().getData());
    }

    @Test
    void deleteFresher() {
        ResponseEntity<ResponseObject> response = fresherController.deleteFresher(anyString());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("OK", response.getBody().getStatus());
        assertEquals("Delete fresher successfully", response.getBody().getMsg());
        assertEquals("", response.getBody().getData());
        verify(fresherService, times(1)).deleteFresher(anyString());
    }

    @Test
    void getScore() {
        when(fresherService.getScore(anyString())).thenReturn(Collections.emptyList());

        ResponseEntity<ResponseObject> response = fresherController.getScore(anyString());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("OK", response.getBody().getStatus());
        assertEquals("Get score of fresher successfully", response.getBody().getMsg());
        assertEquals(Collections.emptyList(),response.getBody().getData());
    }

    @Test
    void searchFresher() {
        when(fresherService.searchFresher(anyString(),anyString(),anyString())).thenReturn(Collections.emptyList());

        ResponseEntity<ResponseObject> response = fresherController.searchFresher(anyString(),anyString(),anyString());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("OK", response.getBody().getStatus());
        assertEquals("Search fresher successfully", response.getBody().getMsg());
        assertEquals(Collections.emptyList(),response.getBody().getData());
    }

    @Test
    void statisticalFresher() {
        when(fresherService.statisticalFresherWithScore(any(),any(),any())).thenReturn(Collections.emptyList());

        ResponseEntity<ResponseObject> response = fresherController.statisticalFresher(any(),any(),any());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("OK", response.getBody().getStatus());
        assertEquals("statistical fresher by score successfully", response.getBody().getMsg());
        assertEquals(Collections.emptyList(),response.getBody().getData());
    }
}