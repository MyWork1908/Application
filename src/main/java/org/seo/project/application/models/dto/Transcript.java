package org.seo.project.application.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.seo.project.application.models.entity.Score;

@Setter @Getter
@NoArgsConstructor
public class Transcript {
    private Double score01;
    private Double score02;
    private Double score03;
    private Double average;

    public Transcript(Score score) {
        this.score01 = score.getScore01();
        this.score02 = score.getScore02();
        this.score03 = score.getScore03();
        this.average = (double)Math.round(((score01+score02+score03)/3) * 100) / 100;
    }
}
