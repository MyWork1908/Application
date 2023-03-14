package org.seo.project.application.service;

import org.seo.project.application.models.entity.Score;
import org.seo.project.application.models.response.CenterResponse;
import org.seo.project.application.models.response.ScoreResponse;

public interface LinkService {
    CenterResponse addFresherToCenter(String fresherId, String centerId);

    ScoreResponse addScore(Score score, String fresherId, String subjectId);
}
