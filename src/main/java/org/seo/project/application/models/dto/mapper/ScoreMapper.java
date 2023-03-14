package org.seo.project.application.models.dto.mapper;

import org.seo.project.application.models.dto.ScoreDTO;
import org.seo.project.application.models.entity.Score;

public class ScoreMapper {
    public Score toScore(ScoreDTO scoreDTO) {
        Score score = new Score();
        score.setScore01(scoreDTO.getScore01());
        score.setScore02(scoreDTO.getScore02());
        score.setScore03(scoreDTO.getScore03());
        return score;
    }
}
