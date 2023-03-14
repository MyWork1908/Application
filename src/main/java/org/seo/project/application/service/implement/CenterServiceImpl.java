package org.seo.project.application.service.implement;

import org.seo.project.application.exception.NotFoundException;
import org.seo.project.application.exception.NotImplementedException;
import org.seo.project.application.models.data.ErrorMessages;
import org.seo.project.application.models.entity.Center;
import org.seo.project.application.models.response.CenterResponse;
import org.seo.project.application.repositories.CenterRepository;
import org.seo.project.application.service.CenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CenterServiceImpl implements CenterService {
    @Autowired
    CenterRepository centerRepository;
    @Override
    public List<Center> getCenter(String id) {
        List<Center> centers = centerRepository.findAll();
        if(centers.isEmpty()) {
            throw new NotFoundException(ErrorMessages.LIST_CENTER_NULL_ERROR);
        }
        if(id != null) {
            centers = centers.stream()
                    .filter(center -> center.getId().contains(id))
                    .toList();
        }
        if(centers.isEmpty()) {
            throw new NotFoundException(ErrorMessages.CENTER_NULL_ERROR);
        }
        return centers;
    }

    @Override
    public Center insertCenter(Center center) {
        Optional<Center> optionalCenter = centerRepository.findById(center.getId());
        if(optionalCenter.isPresent()){
            throw new NotImplementedException(ErrorMessages.CENTER_EXISTS_ERROR);
        }
        return centerRepository.save(center);
    }
    @Override
    public void deleteCenter(String id) {
        boolean exists = centerRepository.existsById(id);
        if(!exists) {
            throw new NotFoundException(ErrorMessages.CENTER_NULL_ERROR);
        }
        centerRepository.deleteById(id);
    }
    @Override
    public Center editCenter(Center center) {
        return centerRepository.findById(center.getId())
                .map(c -> {
                    c.setName(center.getName());
                    c.setAddress(center.getAddress());
                    c.setPhone(center.getPhone());
                    return centerRepository.save(c);
                }).orElseGet(()->centerRepository.save(center));
    }
    @Override
    public CenterResponse getAllFresherOfCenter(String id){
        Optional<Center> optionalCenter = centerRepository.findById(id);
        if(optionalCenter.isPresent()) {
            return new CenterResponse(optionalCenter.get());
        }
        throw new NotFoundException(ErrorMessages.CENTER_NULL_ERROR);
    }
}
