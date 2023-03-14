package org.seo.project.application.service.implement;

import org.seo.project.application.exception.NotFoundException;
import org.seo.project.application.exception.NotImplementedException;
import org.seo.project.application.models.data.ErrorMessages;
import org.seo.project.application.models.entity.Fresher;
import org.seo.project.application.models.response.ScoreResponse;
import org.seo.project.application.repositories.FresherRepository;
import org.seo.project.application.service.FresherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class FresherServiceImpl implements FresherService {
    @Autowired
    FresherRepository fresherRepository;
    @Override
    public List<Fresher> getFresher(String id) {
        List<Fresher> freshers = fresherRepository.findAll();
        if(freshers.isEmpty()) {
            throw new NotFoundException(ErrorMessages.LIST_FRESHER_NULL_ERROR);
        }
        if(id != null) {
            freshers = freshers.stream()
                    .filter(fresher -> fresher.getId().contains(id))
                    .toList();
        }
        if(freshers.isEmpty()) {
            throw new NotFoundException(ErrorMessages.FRESHER_NULL_ERROR);
        }
        return freshers;
    }
    @Override
    public Fresher insertFresher(Fresher fresher) {
        Optional<Fresher> optionalFresher = fresherRepository.findById(fresher.getId());
        if(optionalFresher.isPresent()){
            throw new NotImplementedException(ErrorMessages.FRESHER_EXISTS_ERROR);
        }
        return fresherRepository.save(fresher);
    }

    @Override
    public void deleteFresher(String id) {
        boolean exists = fresherRepository.existsById(id);
        if(!exists){
            throw new NotFoundException(ErrorMessages.FRESHER_NULL_ERROR);
        }
        fresherRepository.deleteById(id);
    }
    @Override
    public Fresher editFresher(Fresher fresher) {
        return fresherRepository.findById(fresher.getId())
                .map(f -> {
                    f.setName(fresher.getName());
                    f.setAddress(fresher.getAddress());
                    f.setPhone(fresher.getPhone());
                    f.setEmail(fresher.getEmail());
                    return fresherRepository.save(f);
                }).orElseGet(()-> fresherRepository.save(fresher));
    }
    @Override
    public List<ScoreResponse> getScore(String id) {
        List<ScoreResponse> list = new ArrayList<>();
        List<Fresher> freshers = fresherRepository.findAll();
        if(freshers.isEmpty()) {
            throw new NotFoundException(ErrorMessages.LIST_FRESHER_NULL_ERROR);
        }
        for (Fresher fresher:freshers) {
            list.add(new ScoreResponse(fresher));
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
    @Override
    public List<Fresher> searchFresher(String name, String email, String lp) {
        Specification<Fresher> specification = Specification.where(null);
        if(name != null && !name.isEmpty()) {
            specification = specification.and(FresherRepository.FresherSpecification.hasName(name));
        }
        if(email != null && !email.isEmpty()) {
            specification = specification.and(FresherRepository.FresherSpecification.hasEmail(email));
        }
        if(lp != null && !lp.isEmpty()) {
            specification = specification.and(FresherRepository.FresherSpecification.hasLanguageProgramming(lp));
        }
        List<Fresher> freshers = fresherRepository.findAll(specification);
        if(freshers.isEmpty()) {
            throw new NotFoundException(ErrorMessages.FRESHER_NULL_ERROR);
        }
        return freshers;
    }
    @Override
    public List<Fresher> statisticalFresherWithScore(Double equal, Double up, Double down) {
        Specification<Fresher> specification = Specification.where(null);
        if(equal != null && up == null && down == null) {
            specification = specification
                    .and(FresherRepository.FresherSpecification.hasScoreEqual(equal));
        }
        if(equal == null && up != null && down == null) {
            specification = specification
                    .and(FresherRepository.FresherSpecification.hasScoreUp(up));
        }
        if (equal == null && up == null && down !=null) {
            specification = specification
                    .and(FresherRepository.FresherSpecification.hasScoreDown(down));
        }
        if (equal == null && up != null && down !=null && up > down) {
            specification = specification
                    .and(FresherRepository.FresherSpecification.hasScoreUpAndDown(up,down));
        }
        List<Fresher> freshers = fresherRepository.findAll(specification);
        if(freshers.isEmpty()) {
            throw new NotFoundException(ErrorMessages.FRESHER_NULL_ERROR);
        }
        return freshers;
    }
}
