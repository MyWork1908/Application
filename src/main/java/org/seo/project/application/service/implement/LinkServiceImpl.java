package org.seo.project.application.service.implement;

import org.seo.project.application.exception.NotFoundException;
import org.seo.project.application.exception.NotImplementedException;
import org.seo.project.application.models.data.ErrorMessages;
import org.seo.project.application.models.entity.Center;
import org.seo.project.application.models.entity.Fresher;
import org.seo.project.application.models.entity.Score;
import org.seo.project.application.models.entity.Subject;
import org.seo.project.application.models.response.CenterResponse;
import org.seo.project.application.models.response.ScoreResponse;
import org.seo.project.application.repositories.CenterRepository;
import org.seo.project.application.repositories.FresherRepository;
import org.seo.project.application.repositories.SubjectRepository;
import org.seo.project.application.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class LinkServiceImpl implements LinkService {
    @Autowired
    FresherRepository fresherRepository;
    @Autowired
    CenterRepository centerRepository;
    @Autowired
    SubjectRepository subjectRepository;
    @Override
    public CenterResponse addFresherToCenter(String fresherId, String centerId) {
        Optional<Fresher> optionalFresher = fresherRepository.findById(fresherId);
        Optional<Center> optionalCenter = centerRepository.findById(centerId);
        if(optionalFresher.isEmpty()) {
            throw new NotFoundException(ErrorMessages.FRESHER_NULL_ERROR);
        }
        if(optionalCenter.isEmpty()){
            throw new NotFoundException(ErrorMessages.CENTER_NULL_ERROR);
        }
        Fresher fresher = optionalFresher.get();
        Center center = optionalCenter.get();
        List<Center> centers = fresher
                .getCenters().stream()
                .filter(c -> c.getId().contains(centerId))
                .toList();
        if(!centers.isEmpty()) {
            throw new NotImplementedException(ErrorMessages.FRESHER_CENTER_EXISTS_ERROR);
        }
        fresher.getCenters().add(center);
        fresherRepository.save(fresher);
        return new CenterResponse(center);
    }
    @Override
    public ScoreResponse addScore(Score score, String fresherId, String subjectId) {
        Optional<Fresher> optionalFresher = fresherRepository.findById(fresherId);
        Optional<Subject> optionalSubject = subjectRepository.findById(subjectId);
        if (optionalFresher.isEmpty())
            throw new NotFoundException(ErrorMessages.FRESHER_NULL_ERROR);
        if (optionalSubject.isEmpty()) {
            throw new NotFoundException(ErrorMessages.SUBJECT_NULL_ERROR);
        }
        Fresher fresher = optionalFresher.get();
        Subject subject = optionalSubject.get();
        List<Score> scores = fresher.getScores().stream()
                .filter(sc -> sc.getSubject().getId().contains(subjectId))
                .toList();
        if (!scores.isEmpty())
            throw new NotImplementedException(ErrorMessages.SCORE_EXISTS_ERROR);
        score.setSubject(subject);
        score.setFresher(fresher);
        fresher.getScores().add(score);
        fresherRepository.saveAndFlush(fresher);
        return new ScoreResponse(fresher);
    }
}
