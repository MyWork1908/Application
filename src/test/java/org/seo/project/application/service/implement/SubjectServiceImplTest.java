package org.seo.project.application.service.implement;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.seo.project.application.exception.NotFoundException;
import org.seo.project.application.exception.NotImplementedException;
import org.seo.project.application.models.entity.Fresher;
import org.seo.project.application.models.entity.Score;
import org.seo.project.application.models.entity.Subject;
import org.seo.project.application.models.response.ScoreResponse;
import org.seo.project.application.repositories.SubjectRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SubjectServiceImplTest {
    @Mock
    SubjectRepository subjectRepository;
    @InjectMocks
    SubjectServiceImpl subjectService;
    private Subject subject;
    private Score score;
    private Fresher fresher;
    private String id;

    @BeforeEach
    void setUp() {
        subject = new Subject("JV01","JAVA",null);
        score = new Score(1L,5d,6d,7d,null,null);
        fresher = new Fresher("2019606150","","","","",null,null);
        id = subject.getId();
    }

    @Test
    void getSubject_allSubject() {
        List<Subject> subjects = new ArrayList<>();
        subjects.add(mock(Subject.class));
        subjects.add(mock(Subject.class));
        when(subjectRepository.findAll()).thenReturn(subjects);
        List<Subject> subjectList = subjectService.getSubject(null);
        int size = subjectList.size();
        assertThat(size).isEqualTo(subjects.size());
    }

    @Test
    void getSubject(){
        String id = subject.getId();
        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject);

        when(subjectRepository.findAll()).thenReturn(subjects);

        subjects = subjects.stream()
                .filter(s -> s.getId().contains(id))
                .toList();

        List<Subject> subjectList = subjectService.getSubject(id);
        int size = subjectList.size();
        assertThat(size).isEqualTo(subjects.size());
    }

    @Test
    void getSubject_allSubject_whenException_listEmpty() {
        List<Subject> subjects = new ArrayList<>();
        when(subjectRepository.findAll()).thenReturn(subjects);
        assertThrows(NotFoundException.class, ()->{
            subjectService.getSubject(null);
        });
    }

    @Test
    void getSubject_whenException_subjectEmpty() {
        List<Subject> subjects = new ArrayList<>();
        subjects.add(new Subject("C1","C",null));

        when(subjectRepository.findAll()).thenReturn(subjects);

        assertThrows(NotFoundException.class,()->{
           subjectService.getSubject(id);
        });
    }
    @Test
    void insertSubject() {
        when(subjectRepository.findById(id)).thenReturn(Optional.empty());
        when(subjectRepository.save(subject)).thenReturn(subject);
        Subject newSubject = subjectService.insertSubject(subject);
        assertThat(newSubject).isNotNull();
    }

    @Test
    void insertSubject_whenSubjectExists() {
        when(subjectRepository.findById(id)).thenReturn(Optional.of(subject));

        assertThrows(NotImplementedException.class,()->{
           subjectService.insertSubject(subject);
        });
    }

    @Test
    void deleteSubject() {
        when(subjectRepository.existsById(id)).thenReturn(true);
        subjectService.deleteSubject(id);
        verify(subjectRepository).deleteById(id);
    }

    @Test
    void deleteSubject_whenSubjectEmpty() {
        when(subjectRepository.existsById(id)).thenReturn(false);
        assertThrows(NotFoundException.class,()->{
            subjectService.deleteSubject(id);
        });
    }
    @Test
    void editSubject() {
        when(subjectRepository.findById(id)).thenReturn(Optional.of(subject));
        Subject editSubject = subjectRepository.findById(subject.getId())
                .map(s -> {
                    s.setLp(subject.getLp());
                    return s;
                }).orElseGet(()->subjectRepository.save(subject));
        Subject upSubject = subjectService.editSubject(subject);
        assertThat(editSubject).isEqualTo(upSubject);
    }

    @Test
    void editSubject_whenSubjectEmpty() {
        when(subjectRepository.findById(id)).thenReturn(Optional.empty());
        Subject editSubject = subjectRepository.findById(subject.getId())
                .map(s -> {
                    s.setLp(subject.getLp());
                    return s;
                }).orElseGet(()->subjectRepository.save(subject));
        Subject upSubject = subjectService.editSubject(subject);
        assertThat(editSubject).isEqualTo(upSubject);
    }

    @Test
    void getScore() {
        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject);
        List<ScoreResponse> list = new ArrayList<>();
        subject.setScores(Lists.newArrayList(score));
        score.setFresher(fresher);
        when(subjectRepository.findAll()).thenReturn(subjects);

        for (Subject subject:subjects) {
            list.add(new ScoreResponse(subject));
        }
        list = list.stream()
                .filter(l->l.getId().contains(id))
                .toList();
        List<ScoreResponse> responseList = subjectService.getScore(id);
        int size = responseList.size();
        assertThat(size).isEqualTo(list.size());
    }
    @Test
    void getScore_Exception_whenListFresherEmpty() {
        List<Subject> subjects = new ArrayList<>();
        when(subjectRepository.findAll()).thenReturn(subjects);
        assertThrows(NotFoundException.class,()->{
            subjectService.getScore(id);
        });
    }
    @Test
    void getScore_Exception_whenListScoreEmpty() {
        List<Subject> subjects = new ArrayList<>();
        subjects.add(subject);
        subject.setScores(Lists.newArrayList(score));
        score.setFresher(fresher);
        when(subjectRepository.findAll()).thenReturn(subjects);
        assertThrows(NotFoundException.class,()->{
            subjectService.getScore("id");
        });
    }
}