package org.seo.project.application.service.implement;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.seo.project.application.exception.NotFoundException;
import org.seo.project.application.exception.NotImplementedException;
import org.seo.project.application.models.entity.Center;
import org.seo.project.application.models.response.CenterResponse;
import org.seo.project.application.repositories.CenterRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CenterServiceImplTest {
    @Mock
    CenterRepository centerRepository;
    @InjectMocks
    CenterServiceImpl centerService;
    private Center center;

    @BeforeEach
    void setUp() {
        center = new Center("AA","","","",null);
    }

    @Test
    void getCenter_allCenter() {
        List<Center> centers = new ArrayList<>();
        centers.add(mock(Center.class));
        centers.add(mock(Center.class));

        when(centerRepository.findAll()).thenReturn(centers);

        List<Center> centerList = centerService.getCenter(null);
        int size = centerList.size();
        assertThat(size).isEqualTo(centers.size());
    }

    @Test
    void getCenter_Center() {
        String id = center.getId();
        List<Center> centers = new ArrayList<>();
        centers.add(center);

        when(centerRepository.findAll()).thenReturn(centers);
        centers = centers.stream()
                .filter(center -> center.getId().contains(id))
                .toList();

        List<Center> centerList = centerService.getCenter(id);
        int size = centerList.size();
        assertThat(size).isEqualTo(centers.size());
    }
    @Test
    void getCenter_allCenter_listEmpty() {
        List<Center> centers = new ArrayList<>();
        when(centerRepository.findAll()).thenReturn(centers);
        assertThrows(NotFoundException.class, ()->{
           centerService.getCenter(null);
        });
    }

    @Test
    void getCenter_Center_notFound() {
        String id = center.getId();
        List<Center> centers = new ArrayList<>();
        centers.add(new Center("A1","","","",null));
        when(centerRepository.findAll()).thenReturn(centers);
        assertThrows(NotFoundException.class, ()->{
            centerService.getCenter(id);
        });
    }

    @Test
    void insertCenter() {
        String id = center.getId();
        when(centerRepository.findById(id)).thenReturn(Optional.empty());
        when(centerRepository.save(center)).thenReturn(center);
        Center saveCenter = centerService.insertCenter(center);
        assertThat(saveCenter).isNotNull();
    }

    @Test
    void insertCenter_whenCenterExists() {
        String id = center.getId();
        when(centerRepository.findById(id)).thenReturn(Optional.of(center));

        assertThrows(NotImplementedException.class,()->{
            centerService.insertCenter(center);
        });
    }
    @Test
    void deleteCenter() {
        String id = center.getId();
        when(centerRepository.existsById(id)).thenReturn(true);
        centerService.deleteCenter(id);
        verify(centerRepository).deleteById(id);
    }

    @Test
    void deleteCenter_whenCenterNotExists() {
        String id = center.getId();
        when(centerRepository.existsById(id)).thenReturn(false);
        assertThrows(NotFoundException.class,()->{
            centerService.deleteCenter(id);
        });
    }

    @Test
    void editCenter() {
        String id = center.getId();
        when(centerRepository.findById(id)).thenReturn(Optional.of(center));
        when(centerRepository.save(center)).thenReturn(center);
        Center editCenter = centerRepository.findById(id)
                .map(c -> {
                    c.setName(center.getName());
                    c.setAddress(center.getAddress());
                    c.setPhone(center.getPhone());
                    return centerRepository.save(c);
                }).orElseGet(()->centerRepository.save(center));
        Center upCenter = centerService.editCenter(center);
        assertThat(upCenter).isEqualTo(editCenter);
    }

    @Test
    void editCenter_whenCenterNotExists() {
        String id = center.getId();
        when(centerRepository.findById(id)).thenReturn(Optional.empty());
        when(centerRepository.save(center)).thenReturn(center);
        Center editCenter = centerRepository.findById(id)
                .map(c -> {
                    c.setName(center.getName());
                    c.setAddress(center.getAddress());
                    c.setPhone(center.getPhone());
                    return centerRepository.save(c);
                }).orElseGet(()->centerRepository.save(center));
        Center upCenter = centerService.editCenter(center);
        assertThat(upCenter).isEqualTo(editCenter);
    }

    @Test
    void getAllFresherOfCenter() {
        String id = center.getId();
        when(centerRepository.findById(id)).thenReturn(Optional.of(center));
        CenterResponse centerResponse = centerService.getAllFresherOfCenter(id);
        assertThat(centerResponse).isNotNull();
    }
    @Test
    void getAllFresherOfCenter_() {
        String id = center.getId();
        when(centerRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, ()->{
            centerService.getAllFresherOfCenter(id);
        });
    }
}