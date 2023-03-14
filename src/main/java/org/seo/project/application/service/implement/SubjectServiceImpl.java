package org.seo.project.application.service.implement;

import org.seo.project.application.exception.NotFoundException;
import org.seo.project.application.exception.NotImplementedException;
import org.seo.project.application.models.data.ErrorMessages;
import org.seo.project.application.models.entity.Subject;
import org.seo.project.application.models.response.ScoreResponse;
import org.seo.project.application.repositories.SubjectRepository;
import org.seo.project.application.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    SubjectRepository subjectRepository;
    @Override
    public List<Subject> getSubject(String id) {
        List<Subject> subjects = subjectRepository.findAll();
        if(subjects.isEmpty()) {
            throw new NotFoundException(ErrorMessages.LIST_SUBJECT_NULL_ERROR);
        }
        if(id != null) {
            subjects = subjects.stream()
                    .filter(subject -> subject.getId().contains(id))
                    .toList();
        }
        if(subjects.isEmpty()) {
            throw new NotFoundException(ErrorMessages.SUBJECT_NULL_ERROR);
        }
        return subjects;
    }
    @Override
    public Subject insertSubject(Subject subject) {
        Optional<Subject> optionalSubject = subjectRepository.findById(subject.getId());
        if(optionalSubject.isEmpty()) {
            return subjectRepository.save(subject);
        }
        throw new NotImplementedException(ErrorMessages.SUBJECT_EXISTS_ERROR);
    }
    @Override
    public void deleteSubject(String id) {
        boolean exists = subjectRepository.existsById(id);
        if (!exists) {
            throw new NotFoundException(ErrorMessages.SUBJECT_NULL_ERROR);
        }
        subjectRepository.deleteById(id);
    }
    @Override
    public Subject editSubject(Subject subject) {
        return subjectRepository.findById(subject.getId())
                .map(s -> {
                    s.setLp(subject.getLp());
                    return s;
                }).orElseGet(()->subjectRepository.save(subject));
    }
    @Override
    public List<ScoreResponse> getScore(String id) {
        List<ScoreResponse> list = new ArrayList<>();
        List<Subject> subjects = subjectRepository.findAll();
        if(subjects.isEmpty()) {
            throw new NotFoundException(ErrorMessages.LIST_SUBJECT_NULL_ERROR);
        }
        for (Subject subject:subjects) {
            list.add(new ScoreResponse(subject));
        }
        if(id != null){
            list = list.stream()
                    .filter(l->l.getId().contains(id))
                    .toList();
        }
        if(list.isEmpty()) {
            throw new NotFoundException(ErrorMessages.LIST_SCORE_NULL_ERROR);
        }
        return list;
    }
}
