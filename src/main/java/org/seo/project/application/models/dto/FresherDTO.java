package org.seo.project.application.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class FresherDTO {
    private String id;
    private String name;
    private String address;
    private String phone;
    private String email;
}
