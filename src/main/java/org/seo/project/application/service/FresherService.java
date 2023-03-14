package org.seo.project.application.service;

import org.seo.project.application.models.entity.Fresher;
import org.seo.project.application.models.response.ScoreResponse;

import java.util.List;

public interface FresherService {
    List<Fresher> getFresher(String id);

    Fresher insertFresher(Fresher fresher);

    void deleteFresher(String id);

    Fresher editFresher(Fresher fresher);

    List<ScoreResponse> getScore(String id);

    List<Fresher> searchFresher(String name, String email, String lp);

    List<Fresher> statisticalFresherWithScore(Double equal, Double up, Double down);
}
