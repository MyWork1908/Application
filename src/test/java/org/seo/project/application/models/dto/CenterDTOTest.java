package org.seo.project.application.models.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CenterDTOTest {
    private CenterDTO centerDTO;
    @BeforeEach
    void setUp() {
        centerDTO = new CenterDTO();
    }
    @Test
    void test_getId_setId() {
        centerDTO.setId("123");
        assertEquals("123",centerDTO.getId());
    }

    @Test
    void test_getName_setName() {
        centerDTO.setName("huy");
        assertEquals("huy",centerDTO.getName());
    }

    @Test
    void test_getAddress_setAddress() {
        centerDTO.setAddress("Ha Noi");
        assertEquals("Ha Noi",centerDTO.getAddress());
    }

    @Test
    void test_getPhone_setPhone() {
        centerDTO.setPhone("0971516704");
        assertEquals("0971516704",centerDTO.getPhone());
    }
    @Test
    void test_constructor() {
        centerDTO = new CenterDTO("123","huy","Ha Noi","0971516704");
        assertEquals("123",centerDTO.getId());
        assertEquals("huy",centerDTO.getName());
        assertEquals("Ha Noi",centerDTO.getAddress());
        assertEquals("0971516704",centerDTO.getPhone());
    }

}