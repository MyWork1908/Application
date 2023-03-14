package org.seo.project.application.service.implement;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.seo.project.application.exception.NotFoundException;
import org.seo.project.application.exception.NotImplementedException;
import org.seo.project.application.models.entity.Center;
import org.seo.project.application.models.entity.Fresher;
import org.seo.project.application.models.entity.Score;
import org.seo.project.application.models.entity.Subject;
import org.seo.project.application.models.response.CenterResponse;
import org.seo.project.application.models.response.ScoreResponse;
import org.seo.project.application.repositories.CenterRepository;
import org.seo.project.application.repositories.FresherRepository;
import org.seo.project.application.repositories.SubjectRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LinkServiceImplTest {
    @Mock
    FresherRepository fresherRepository;
    @Mock
    CenterRepository centerRepository;
    @Mock
    SubjectRepository subjectRepository;
    @InjectMocks
    LinkServiceImpl linkService;
    private Fresher fresher;
    private Center center;
    private Subject subject;
    private Score score;
    private String fresherId, centerId, subjectId;

    @BeforeEach
    void setUp() {
        center = new Center("A1","","","", null);
        subject = new Subject("JV01","JAVA",null);
        score = new Score(1L,2d,3d,4d,null,null);
        fresher = new Fresher("2019606150","","","","",null,null);
        fresherId = fresher.getId();
        centerId = center.getId();
        subjectId = subject.getId();
    }

    @Test
    void addFresherToCenter() {
        Center newCenter = new Center("A2","","","",null);
        fresher.setCenters(Lists.newArrayList(newCenter));
        when(fresherRepository.findById(fresherId)).thenReturn(Optional.of(fresher));
        when(centerRepository.findById(centerId)).thenReturn(Optional.of(center));
        when(fresherRepository.save(fresher)).thenReturn(fresher);
        CenterResponse centerResponse = linkService.addFresherToCenter(fresherId,centerId);
        assertThat(centerResponse).isNotNull();
    }
    @Test
    void addFresherToCenter_whenFresherNotExists() {
        when(fresherRepository.findById(fresherId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class,()->{
           linkService.addFresherToCenter(fresherId,centerId);
        });
    }
    @Test
    void addFresherToCenter_whenCenterNotExists() {
        when(fresherRepository.findById(fresherId)).thenReturn(Optional.of(fresher));
        when(centerRepository.findById(centerId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class,()->{
            linkService.addFresherToCenter(fresherId,centerId);
        });
    }
    @Test
    void addFresherToCenter_whenFresherInCenter() {
        fresher.setCenters(Lists.newArrayList(center));
        when(fresherRepository.findById(fresherId)).thenReturn(Optional.of(fresher));
        when(centerRepository.findById(centerId)).thenReturn(Optional.of(center));
        assertThrows(NotImplementedException.class,()->{
            linkService.addFresherToCenter(fresherId,centerId);
        });
    }

    @Test
    void addScore() {
        Subject newSubject = new Subject("C01","C",null);
        Score newScore = new Score(2L,2d,3d,4d,null,newSubject);
        fresher.setScores(Lists.newArrayList(newScore));
        when(fresherRepository.findById(fresherId)).thenReturn(Optional.of(fresher));
        when(subjectRepository.findById(subjectId)).thenReturn(Optional.of(subject));
        ScoreResponse scoreResponse = linkService.addScore(score,fresherId,subjectId);
        assertThat(scoreResponse).isNotNull();
    }

    @Test
    void addScore_whenFresherNotExists() {
        when(fresherRepository.findById(fresherId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class,()->{
            linkService.addScore(score,fresherId,subjectId);
        });
    }

    @Test
    void addScore_whenSubjectNotExists() {
        when(fresherRepository.findById(fresherId)).thenReturn(Optional.of(fresher));
        when(subjectRepository.findById(subjectId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class,()->{
            linkService.addScore(score,fresherId,subjectId);
        });
    }

    @Test
    void addScore_whenScoreExists() {
        when(subjectRepository.findById(subjectId)).thenReturn(Optional.of(subject));
        when(fresherRepository.findById(fresherId)).thenReturn(Optional.of(fresher));
        score.setSubject(subject);
        fresher.setScores(Lists.newArrayList(score));
        assertThrows(NotImplementedException.class,()->{
            linkService.addScore(score,fresherId,subjectId);
        });
    }
}
