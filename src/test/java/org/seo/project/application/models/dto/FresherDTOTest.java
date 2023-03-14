package org.seo.project.application.models.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FresherDTOTest {

    private FresherDTO fresherDTO;
    @BeforeEach
    void setUp() {
        fresherDTO = new FresherDTO();
    }

    @Test
    void test_getId_setId() {
        fresherDTO.setId("2019");
        assertEquals("2019",fresherDTO.getId());
    }

    @Test
    void test_getName_setName() {
        fresherDTO.setName("huy");
        assertEquals("huy",fresherDTO.getName());
    }

    @Test
    void test_getAddress_setAddress() {
        fresherDTO.setAddress("Ha Noi");
        assertEquals("Ha Noi",fresherDTO.getAddress());
    }

    @Test
    void test_getPhone_setPhone() {
        fresherDTO.setPhone("0971561704");
        assertEquals("0971561704",fresherDTO.getPhone());
    }

    @Test
    void test_getEmail_setEmail() {
        fresherDTO.setEmail("huyvuvi123@gmail.com");
        assertEquals("huyvuvi123@gmail.com",fresherDTO.getEmail());
    }
    @Test
    void test_Constructor() {
        fresherDTO = new FresherDTO("2019","huy","Ha Noi","0971561704","huyvuvi123@gmail.com");
        assertEquals("2019",fresherDTO.getId());
        assertEquals("huy",fresherDTO.getName());
        assertEquals("Ha Noi",fresherDTO.getAddress());
        assertEquals("0971561704",fresherDTO.getPhone());
        assertEquals("huyvuvi123@gmail.com",fresherDTO.getEmail());
    }
}