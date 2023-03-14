package org.seo.project.application.service;

import org.seo.project.application.models.entity.Subject;
import org.seo.project.application.models.response.ScoreResponse;

import java.util.List;

public interface SubjectService {
    List<Subject> getSubject(String id);

    Subject insertSubject(Subject subject);

    void deleteSubject(String id);

    Subject editSubject(Subject subject);

    List<ScoreResponse> getScore(String id);
}
