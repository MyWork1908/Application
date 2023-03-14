package org.seo.project.application.models.dto.mapper;

import org.seo.project.application.models.dto.CenterDTO;
import org.seo.project.application.models.entity.Center;

public class CenterMapper {
    public Center toCenter(CenterDTO centerDTO) {
        Center center = new Center();
        center.setId(centerDTO.getId());
        center.setName(centerDTO.getName());
        center.setAddress(centerDTO.getAddress());
        center.setPhone(centerDTO.getPhone());
        return center;
    }
}
