package org.seo.project.application.models.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.seo.project.application.models.dto.Transcript;
import org.seo.project.application.models.entity.Fresher;
import org.seo.project.application.models.entity.Score;
import org.seo.project.application.models.entity.Subject;

import java.util.HashMap;
import java.util.Map;

@Setter @Getter
@NoArgsConstructor
public class ScoreResponse {
    private String id;
    private String name;
    private Map<String, Transcript> transcript = new HashMap<>();

    public ScoreResponse(Fresher fresher) {
        this.id = fresher.getId();
        this.name = fresher.getName();
        for (Score score:fresher.getScores()) {
            this.transcript.put(score.getSubject().getLp(),new Transcript(score));
        }
    }
    public ScoreResponse(Subject subject) {
        this.id = subject.getId();
        this.name = subject.getLp();
        for (Score score: subject.getScores()) {
            this.transcript.put(score.getFresher().getName(),new Transcript(score));
        }
    }

}
