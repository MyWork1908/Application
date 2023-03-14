package org.seo.project.application.service;

import org.seo.project.application.models.entity.Center;
import org.seo.project.application.models.response.CenterResponse;

import java.util.List;

public interface CenterService {
    List<Center> getCenter(String id);

    Center insertCenter(Center center);

    void deleteCenter(String id);

    Center editCenter(Center center);

    CenterResponse getAllFresherOfCenter(String id);
}
