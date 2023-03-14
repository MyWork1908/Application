package org.seo.project.application.models.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScoreDTO {
    private String fresherId;
    private String subjectId;
    private Double score01;
    private Double score02;
    private Double score03;
}
