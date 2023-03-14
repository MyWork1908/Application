package org.seo.project.application.models.dto.mapper;

import org.seo.project.application.models.dto.SubjectDTO;
import org.seo.project.application.models.entity.Subject;

public class SubjectMapper {
    public Subject toSubject(SubjectDTO subjectDTO) {
        Subject subject = new Subject();
        subject.setId(subjectDTO.getId());
        subject.setLp(subjectDTO.getLp());
        return subject;
    }
}
