package org.seo.project.application.models.dto.mapper;

import org.seo.project.application.models.dto.FresherDTO;
import org.seo.project.application.models.entity.Fresher;

public class FresherMapper {
    public Fresher toFresher(FresherDTO fresherDTO) {
        Fresher fresher = new Fresher();
        fresher.setId(fresherDTO.getId());
        fresher.setName(fresherDTO.getName());
        fresher.setAddress(fresherDTO.getAddress());
        fresher.setPhone(fresherDTO.getPhone());
        fresher.setEmail(fresherDTO.getEmail());
        return fresher;
    }
}
