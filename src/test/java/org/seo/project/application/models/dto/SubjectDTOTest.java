package org.seo.project.application.models.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubjectDTOTest {
    private SubjectDTO subjectDTO;
    @BeforeEach
    void setUp() {
        subjectDTO = new SubjectDTO();
    }
    @Test
    void getId() {
        subjectDTO.setId("JV01");
        assertEquals("JV01",subjectDTO.getId());
    }

    @Test
    void getLp() {
        subjectDTO.setLp("JAVA");
        assertEquals("JAVA",subjectDTO.getLp());
    }


}