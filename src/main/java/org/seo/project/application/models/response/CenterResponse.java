package org.seo.project.application.models.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.seo.project.application.models.entity.Center;
import org.seo.project.application.models.entity.Fresher;

import java.util.Collection;

@Setter @Getter
@NoArgsConstructor
public class CenterResponse {
    private String name;
    private String address;
    private String phone;
    private Collection<Fresher> freshers;

    public CenterResponse(Center center) {
        this.name = center.getName();
        this.address = center.getAddress();
        this.phone = center.getPhone();
        this.freshers = center.getFreshers();
    }
}
