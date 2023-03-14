package org.seo.project.application.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.seo.project.application.models.entity.Fresher;
import org.seo.project.application.models.entity.Score;
import org.seo.project.application.models.entity.Subject;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FresherRepositoryTest {
    private static final String SCORE1 = "score1";
    private static final String SCORE2 = "score2";
    private static final String SCORE3 = "score3";
    private static final String SCORES = "scores";
    private static final String ROUND = "ROUND";
    @Mock
    private Root<Fresher> root;
    @Mock
    private CriteriaQuery<Fresher> query;
    @Mock
    private CriteriaBuilder builder;

    private Predicate predicate;
    private Join<Fresher, Score> scoreJoin;
    private Expression<Double> sumScores;
    private Expression<Number> avgScore;
    private Expression<Double> roundedAvgScore;

    @BeforeEach
    void setUp() {
        scoreJoin = mock(Join.class);
        sumScores = mock(Expression.class);
        avgScore = mock(Expression.class);
        roundedAvgScore = mock(Expression.class);
        predicate = mock(Predicate.class);
    }

    @Test
    void testHasName() {
        String name = "huy";
        when(builder.like(root.get("name"), "%" + name + "%")).thenReturn(predicate);
        Specification<Fresher> spec = FresherRepository.FresherSpecification.hasName(name);
        Predicate result = spec.toPredicate(root, query, builder);
        assertEquals(predicate,result);
    }
    @Test
    void testHasEmail() {
        String email = "huyvuvi123@gmail.com";
        when(builder.like(root.get("email"), "%" + email + "%")).thenReturn(predicate);
        Specification<Fresher> spec = FresherRepository.FresherSpecification.hasEmail(email);
        Predicate result = spec.toPredicate(root, query, builder);
        assertEquals(predicate,result);
    }
    @Test
    void testHasLanguageProgramming() {
        String lp = "JAVA";
        Join<Fresher, Score> scoreJoin = mock(Join.class);
        Join<Score, Subject> subjectJoin = mock(Join.class);

        when(root.join("scores")).thenReturn((Join)scoreJoin);
        when(scoreJoin.join("subject")).thenReturn((Join) subjectJoin);
        when(builder.equal(subjectJoin.get("lp"), lp)).thenReturn(predicate);
        Specification<Fresher> spec = FresherRepository.FresherSpecification.hasLanguageProgramming(lp);
        Predicate result = spec.toPredicate(root, query, builder);
        assertEquals(predicate,result);
    }

    @Test
    void testHasScoreEqual() {
        Double equal = 5d;
        when(root.join(SCORES)).thenReturn((Join) scoreJoin);
        when(builder.sum(scoreJoin.get(SCORE1), scoreJoin.get(SCORE2))).thenReturn((Expression)sumScores);
        when(builder.sum(sumScores, scoreJoin.get(SCORE3))).thenReturn(sumScores);
        when(builder.quot(sumScores, BigDecimal.valueOf(3))).thenReturn(avgScore);
        when(builder.function(ROUND, Double.class, avgScore.as(BigDecimal.class),
                builder.literal(2))).thenReturn(roundedAvgScore);
        when(query.distinct(true)).thenReturn(query);
        when(query.groupBy(root.get("id"), scoreJoin.get(SCORE1), scoreJoin.get(SCORE2), scoreJoin.get(SCORE3)))
                .thenReturn(query);
        when(builder.equal(roundedAvgScore, equal)).thenReturn(predicate);
        when(query.having(predicate)).thenReturn(query);
        when(query.getRestriction()).thenReturn(predicate);

        Specification<Fresher> spec = FresherRepository.FresherSpecification.hasScoreEqual(equal);
        Predicate result = spec.toPredicate(root, query, builder);
        assertEquals(predicate,result);
    }
    @Test
    void testHasScoreUp() {
        Double up = 5d;
        when(root.join(SCORES)).thenReturn((Join) scoreJoin);
        when(builder.sum(scoreJoin.get(SCORE1), scoreJoin.get(SCORE2))).thenReturn((Expression)sumScores);
        when(builder.sum(sumScores, scoreJoin.get(SCORE3))).thenReturn(sumScores);
        when(builder.quot(sumScores, BigDecimal.valueOf(3))).thenReturn(avgScore);
        when(builder.function(ROUND, Double.class, avgScore.as(BigDecimal.class),
                builder.literal(2))).thenReturn(roundedAvgScore);
        when(query.distinct(true)).thenReturn(query);
        when(query.groupBy(root.get("id"), scoreJoin.get(SCORE1), scoreJoin.get(SCORE2), scoreJoin.get(SCORE3)))
                .thenReturn(query);
        when(builder.lessThan(roundedAvgScore, up)).thenReturn(predicate);
        when(query.having(predicate)).thenReturn(query);
        when(query.getRestriction()).thenReturn(predicate);

        Specification<Fresher> spec = FresherRepository.FresherSpecification.hasScoreUp(up);
        Predicate result = spec.toPredicate(root, query, builder);
        assertEquals(predicate,result);
    }

    @Test
    void testHasScoreDown() {
        Double down = 5d;
        when(root.join(SCORES)).thenReturn((Join) scoreJoin);
        when(builder.sum(scoreJoin.get(SCORE1), scoreJoin.get(SCORE2))).thenReturn((Expression)sumScores);
        when(builder.sum(sumScores, scoreJoin.get(SCORE3))).thenReturn(sumScores);
        when(builder.quot(sumScores, BigDecimal.valueOf(3))).thenReturn(avgScore);
        when(builder.function(ROUND, Double.class, avgScore.as(BigDecimal.class),
                builder.literal(2))).thenReturn(roundedAvgScore);
        when(query.distinct(true)).thenReturn(query);
        when(query.groupBy(root.get("id"), scoreJoin.get(SCORE1), scoreJoin.get(SCORE2), scoreJoin.get(SCORE3)))
                .thenReturn(query);
        when(builder.greaterThan(roundedAvgScore, down)).thenReturn(predicate);
        when(query.having(predicate)).thenReturn(query);
        when(query.getRestriction()).thenReturn(predicate);

        Specification<Fresher> spec = FresherRepository.FresherSpecification.hasScoreDown(down);
        Predicate result = spec.toPredicate(root, query, builder);
        assertEquals(predicate,result);
    }

    @Test
    void testHasScoreUpAndDown() {
        Double up = 7d;
        Double down = 4d;
        when(root.join(SCORES)).thenReturn((Join) scoreJoin);
        when(builder.sum(scoreJoin.get(SCORE1), scoreJoin.get(SCORE2))).thenReturn((Expression)sumScores);
        when(builder.sum(sumScores, scoreJoin.get(SCORE3))).thenReturn(sumScores);
        when(builder.quot(sumScores, BigDecimal.valueOf(3))).thenReturn(avgScore);
        when(builder.function(ROUND, Double.class, avgScore.as(BigDecimal.class),
                builder.literal(2))).thenReturn(roundedAvgScore);
        when(query.distinct(true)).thenReturn(query);
        when(query.groupBy(root.get("id"), scoreJoin.get(SCORE1), scoreJoin.get(SCORE2), scoreJoin.get(SCORE3)))
                .thenReturn(query);
        when(builder.between(roundedAvgScore, down, up)).thenReturn(predicate);
        when(query.having(predicate)).thenReturn(query);
        when(query.getRestriction()).thenReturn(predicate);

        Specification<Fresher> spec = FresherRepository.FresherSpecification.hasScoreUpAndDown(up, down);
        Predicate result = spec.toPredicate(root, query, builder);
        assertEquals(predicate,result);
    }
}