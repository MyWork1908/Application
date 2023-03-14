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
import org.seo.project.application.models.entity.Fresher;
import org.seo.project.application.models.entity.Score;
import org.seo.project.application.models.entity.Subject;
import org.seo.project.application.models.response.ScoreResponse;
import org.seo.project.application.repositories.FresherRepository;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FresherServiceImplTest {
    @Mock
    FresherRepository fresherRepository;
    @InjectMocks
    FresherServiceImpl fresherService;
    private Fresher fresher;
    private Score score;
    private Subject subject;
    private String id;
    @BeforeEach
    void setUp() {
        subject = new Subject("JV01","JAVA",null);
        score = new Score(1L,5d,5d,5d,fresher,subject);
        fresher = new Fresher("2019606150","Huy","Thái Bình","0977561345","huyvuvi123@gmail.com",null, Lists.newArrayList(score));
        id = fresher.getId();
        subject.setScores(Lists.newArrayList(score));
    }
    @Test
    void getFresher_allFresher() {
        List<Fresher> freshers = new ArrayList<>();
        freshers.add(mock(Fresher.class));
        freshers.add(mock(Fresher.class));
        when(fresherRepository.findAll()).thenReturn(freshers);
        List<Fresher> fresherList = fresherService.getFresher(null);
        int size = fresherList.size();
        assertThat(size).isEqualTo(freshers.size());
    }
    @Test
    void getFresher() {
        List<Fresher> freshers = new ArrayList<>();
        freshers.add(fresher);

        when(fresherRepository.findAll()).thenReturn(freshers);

        freshers = freshers.stream()
                .filter(fresher -> fresher.getId().contains(id))
                .toList();

        List<Fresher> fresherList = fresherService.getFresher(id);
        int size = fresherList.size();
        assertThat(size).isEqualTo(freshers.size());
    }

    @Test
    void getFresher_allFresher_whenException_listEmpty() {
        List<Fresher> freshers = new ArrayList<>();
        when(fresherRepository.findAll()).thenReturn(freshers);
        assertThrows(NotFoundException.class, ()->{
            fresherService.getFresher(null);
        });
    }

    @Test
    void getFresher_whenException_fresherEmpty() {
        List<Fresher> freshers = new ArrayList<>();
        freshers.add(new Fresher("2090909090","","","","",null,null));

        when(fresherRepository.findAll()).thenReturn(freshers);

        assertThrows(NotFoundException.class, ()->{
            fresherService.getFresher(id);
        });
    }

    @Test
    void insertFresher() {
        when(fresherRepository.findById(id)).thenReturn(Optional.empty());
        when(fresherRepository.save(fresher)).thenReturn(fresher);
        Fresher saveFresher = fresherService.insertFresher(fresher);
        assertThat(saveFresher).isNotNull();
    }
    @Test
    void insertFresher_whenFresherExists() {
        when(fresherRepository.findById(id)).thenReturn(Optional.of(fresher));

        assertThrows(NotImplementedException.class,()->{
           fresherService.insertFresher(fresher);
        });
        verify(fresherRepository, never()).save(fresher);
    }

    @Test
    void deleteFresher() {
        when(fresherRepository.existsById(id)).thenReturn(Boolean.TRUE);
        fresherService.deleteFresher(id);
        verify(fresherRepository).deleteById(id);
    }
    @Test
    void deleteFresher_whenFresherEmpty() {
        when(fresherRepository.existsById(id)).thenReturn(Boolean.FALSE);
        assertThrows(NotFoundException.class,()->{
            fresherService.deleteFresher(id);
        });
    }

    @Test
    void editFresher() {
        when(fresherRepository.findById(id)).thenReturn(Optional.of(fresher));
        when(fresherRepository.save(fresher)).thenReturn(fresher);
        Fresher editFresher = fresherRepository.findById(fresher.getId())
                                .map(f -> {
                                    f.setName(fresher.getName());
                                    f.setAddress(fresher.getAddress());
                                    f.setPhone(fresher.getPhone());
                                    f.setEmail(fresher.getEmail());
                                    return fresherRepository.save(f);
                                }).orElseGet(()-> fresherRepository.save(fresher));
        Fresher upFresher = fresherService.editFresher(fresher);
        assertThat(editFresher).isEqualTo(upFresher);
    }

    @Test
    void editFresher_whenFresherEmpty() {
        when(fresherRepository.findById(id)).thenReturn(Optional.empty());
        when(fresherRepository.save(fresher)).thenReturn(fresher);
        Fresher editFresher = fresherRepository.findById(fresher.getId())
                .map(f -> {
                    f.setName(fresher.getName());
                    f.setAddress(fresher.getAddress());
                    f.setPhone(fresher.getPhone());
                    f.setEmail(fresher.getEmail());
                    return fresherRepository.save(f);
                }).orElseGet(()-> fresherRepository.save(fresher));
        Fresher upFresher = fresherService.editFresher(fresher);
        assertThat(editFresher).isEqualTo(upFresher);
    }

    @Test
    void getScore_allScore() {
        List<Fresher> freshers = new ArrayList<>();
        freshers.add(mock(Fresher.class));
        freshers.add(mock(Fresher.class));
        List<ScoreResponse> list = new ArrayList<>();

        when(fresherRepository.findAll()).thenReturn(freshers);
        for (Fresher fresher:freshers) {
            list.add(new ScoreResponse(fresher));
        }
        List<ScoreResponse> responseList = fresherService.getScore(null);
        int size = responseList.size();
        assertThat(size).isEqualTo(list.size());
    }

    @Test
    void getScore_Score() {
        List<Fresher> freshers = new ArrayList<>();
        freshers.add(fresher);
        List<ScoreResponse> list = new ArrayList<>();

        when(fresherRepository.findAll()).thenReturn(freshers);
        for (Fresher fresher:freshers) {
            list.add(new ScoreResponse(fresher));
        }
        list = list.stream()
                .filter(l->l.getId().contains(id))
                .toList();

        List<ScoreResponse> responseList = fresherService.getScore(id);
        int size = responseList.size();
        assertThat(size).isEqualTo(list.size());
    }

    @Test
    void getScore_whenListFresherEmpty() {
        List<Fresher> freshers = new ArrayList<>();
        when(fresherRepository.findAll()).thenReturn(freshers);
        assertThrows(NotFoundException.class,()->{
           fresherService.getScore(id);
        });
    }
    @Test
    void getScore_whenListScoreEmpty() {
        List<Fresher> freshers = new ArrayList<>();
        freshers.add(fresher);
        when(fresherRepository.findAll()).thenReturn(freshers);
        assertThrows(NotFoundException.class,()->{
            fresherService.getScore("id");
        });
    }

    @Test
    void searchFresher() {
        List<Fresher> freshers = new ArrayList<>();
        freshers.add(fresher);
        when(fresherRepository.findAll(any(Specification.class))).thenReturn(freshers);
        List<Fresher> fresherList = fresherService.searchFresher(fresher.getName(),null,null);
        assertEquals(1, fresherList.size());
        assertEquals("Huy",fresherList.get(0).getName());

        List<Fresher> fresherList2 = fresherService.searchFresher(null,"huyvuvi123@gmail.com",null);
        assertEquals(1, fresherList2.size());
        assertEquals("huyvuvi123@gmail.com",fresherList2.get(0).getEmail());

        List<Fresher> fresherList3 = fresherService.searchFresher(null,null,"JAVA");
        assertEquals(1, fresherList3.size());
        for (Fresher fresher : fresherList3) {
            for (Score score : fresher.getScores()) {
                String lp = score.getSubject().getLp();
                assertEquals("JAVA",lp);
            }
        }
    }

    @Test
    void searchFresher_exception() {
        List<Fresher> freshers = new ArrayList<>();
        when(fresherRepository.findAll(any(Specification.class))).thenReturn(freshers);
        assertThrows(NotFoundException.class,()->{
           fresherService.searchFresher("Hung",null,null);
        });
    }
    @Test
    void statisticalFresherWithScore() {
        List<Fresher> freshers = new ArrayList<>();
        freshers.add(fresher);
        when(fresherRepository.findAll(any(Specification.class))).thenReturn(freshers);

        List<Fresher> fresherList = fresherService.statisticalFresherWithScore(5d,null,null);
        assertEquals(1,fresherList.size());
        for (Fresher fresher:fresherList) {
            for (Score score : fresher.getScores()){
                double values = (double)Math.round(((score.getScore01()+score.getScore02()+score.getScore03())/3) * 100) / 100;
                assertEquals(5d,values);
            }
        }
        List<Fresher> fresherList2 = fresherService.statisticalFresherWithScore(null,6d,null);
        assertEquals(1,fresherList2.size());
        for (Fresher fresher:fresherList2) {
            for (Score score : fresher.getScores()){
                double values = (double)Math.round(((score.getScore01()+score.getScore02()+score.getScore03())/3) * 100) / 100;
                assertTrue(values<6d);
            }
        }
        List<Fresher> fresherList3 = fresherService.statisticalFresherWithScore(null,null,6d);
        assertEquals(1,fresherList3.size());
        for (Fresher fresher:fresherList3) {
            for (Score score : fresher.getScores()){
                double values = (double)Math.round(((score.getScore01()+score.getScore02()+score.getScore03())/3) * 100) / 100;
                assertFalse(values>6d);
            }
        }
        List<Fresher> fresherList4 = fresherService.statisticalFresherWithScore(null,7d,4d);
        assertEquals(1,fresherList4.size());
        for (Fresher fresher:fresherList4) {
            for (Score score : fresher.getScores()){
                double values = (double)Math.round(((score.getScore01()+score.getScore02()+score.getScore03())/3) * 100) / 100;
                assertTrue(values>4d);
                assertTrue(values<7d);
            }
        }
    }
    @Test
    void statisticalFresherWithScore_exception() {
        List<Fresher> freshers = new ArrayList<>();
        when(fresherRepository.findAll(any(Specification.class))).thenReturn(freshers);
        assertThrows(NotFoundException.class,()->{
            fresherService.statisticalFresherWithScore(1d,2d,3d);
        });
    }
}