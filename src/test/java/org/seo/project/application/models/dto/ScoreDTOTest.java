package org.seo.project.application.models.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ScoreDTOTest {
    private ScoreDTO scoreDTO;

    @BeforeEach
    void setUp() {
        scoreDTO = new ScoreDTO();
    }

    @Test
    void test_getFresherId_setFresherId() {
        scoreDTO.setFresherId("2019");
        assertEquals("2019",scoreDTO.getFresherId());
    }

    @Test
    void test_getSubjectId_setSubjectId() {
        scoreDTO.setSubjectId("JV01");
        assertEquals("JV01",scoreDTO.getSubjectId());
    }

    @Test
    void test_getScore01_setScore01() {
        scoreDTO.setScore01(6d);
        assertEquals(6d,scoreDTO.getScore01());
    }

    @Test
    void test_getScore02_setScore02() {
        scoreDTO.setScore02(7d);
        assertEquals(7d,scoreDTO.getScore02());
    }

    @Test
    void test_getScore03_setScore03() {
        scoreDTO.setScore03(8d);
        assertEquals(8d,scoreDTO.getScore03());
    }
    @Test
    void test_constructor() {
        scoreDTO = new ScoreDTO("2019","JV01",6d,7d,8d);
        assertEquals("2019",scoreDTO.getFresherId());
        assertEquals("JV01",scoreDTO.getSubjectId());
        assertEquals(6d,scoreDTO.getScore01());
        assertEquals(7d,scoreDTO.getScore02());
        assertEquals(8d,scoreDTO.getScore03());
    }

}