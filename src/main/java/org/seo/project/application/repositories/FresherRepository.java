package org.seo.project.application.repositories;

import org.seo.project.application.models.entity.Fresher;
import org.seo.project.application.models.entity.Score;
import org.seo.project.application.models.entity.Subject;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import java.math.BigDecimal;

@Repository
public interface FresherRepository extends JpaRepository<Fresher, String>, JpaSpecificationExecutor<Fresher> {
      class FresherSpecification {
          private static final String ROUND = "ROUND";
          private static final String SCORES = "scores";
          private static final String SCORE1 = "score01";
          private static final String SCORE2 = "score02";
          private static final String SCORE3 = "score03";
        private FresherSpecification() {}
        public static Specification<Fresher> hasName(String name) {
            return ((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("name"),"%"+name+"%"));
        }
        public static Specification<Fresher> hasEmail(String email) {
            return ((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("email"),"%"+email+"%"));
        }
        public static Specification<Fresher> hasLanguageProgramming(String lp) {
            return (root, query, criteriaBuilder) -> {
                Join<Fresher, Score> scoreJoin = root.join(SCORES);
                Join<Score, Subject> subjectJoin = scoreJoin.join("subject");
                return criteriaBuilder.equal(subjectJoin.get("lp"), lp);
            };
        }
        public static Specification<Fresher> hasScoreEqual(Double equal) {
            return (root, query, criteriaBuilder) -> {
                Join<Fresher, Score> scoreJoin = root.join(SCORES);
                query.distinct(true);
                Expression<Double> sumScores = criteriaBuilder.sum(
                        criteriaBuilder.sum(scoreJoin.get(SCORE1), scoreJoin.get(SCORE2)),
                        scoreJoin.get(SCORE3)
                );
                Expression<Number> avgScore = criteriaBuilder.quot(sumScores,BigDecimal.valueOf(3));
                Expression<Double> roundedAvgScore = criteriaBuilder.function(ROUND, Double.class,
                        avgScore.as(BigDecimal.class), criteriaBuilder.literal(2));
                query.groupBy(root.get("id"),scoreJoin.get(SCORE1),scoreJoin.get(SCORE2),scoreJoin.get(SCORE3))
                        .having(criteriaBuilder.equal(roundedAvgScore, equal));
                return query.getRestriction();
            };
        }
          public static Specification<Fresher> hasScoreUp(Double up) {
              return (root, query, criteriaBuilder) -> {
                  Join<Fresher, Score> scoreJoin = root.join(SCORES);
                  query.distinct(true);
                  Expression<Double> sumScores = criteriaBuilder.sum(
                          criteriaBuilder.sum(scoreJoin.get(SCORE1), scoreJoin.get(SCORE2)),
                          scoreJoin.get(SCORE3)
                  );
                  Expression<Number> avgScore = criteriaBuilder.quot(sumScores,BigDecimal.valueOf(3));
                  Expression<Double> roundedAvgScore = criteriaBuilder.function(ROUND, Double.class,
                          avgScore.as(BigDecimal.class), criteriaBuilder.literal(2));
                  query.groupBy(root.get("id"),scoreJoin.get(SCORE1),scoreJoin.get(SCORE2),scoreJoin.get(SCORE3))
                          .having(criteriaBuilder.lessThan(roundedAvgScore, up));
                  return query.getRestriction();
              };
          }

          public static Specification<Fresher> hasScoreDown(Double down) {
              return (root, query, criteriaBuilder) -> {
                  Join<Fresher, Score> scoreJoin = root.join(SCORES);
                  query.distinct(true);
                  Expression<Double> sumScores = criteriaBuilder.sum(
                          criteriaBuilder.sum(scoreJoin.get(SCORE1), scoreJoin.get(SCORE2)),
                          scoreJoin.get(SCORE3)
                  );
                  Expression<Number> avgScore = criteriaBuilder.quot(sumScores,BigDecimal.valueOf(3));
                  Expression<Double> roundedAvgScore = criteriaBuilder.function(ROUND, Double.class,
                          avgScore.as(BigDecimal.class), criteriaBuilder.literal(2));
                  query.groupBy(root.get("id"),scoreJoin.get(SCORE1),scoreJoin.get(SCORE2),scoreJoin.get(SCORE3))
                          .having(criteriaBuilder.greaterThan(roundedAvgScore, down));
                  return query.getRestriction();
              };
          }
          public static Specification<Fresher> hasScoreUpAndDown(Double up,Double down) {
              return (root, query, criteriaBuilder) -> {
                  Join<Fresher, Score> scoreJoin = root.join(SCORES);
                  query.distinct(true);
                  Expression<Double> sumScores = criteriaBuilder.sum(
                          criteriaBuilder.sum(scoreJoin.get(SCORE1), scoreJoin.get(SCORE2)),
                          scoreJoin.get(SCORE3)
                  );
                  Expression<Number> avgScore = criteriaBuilder.quot(sumScores,BigDecimal.valueOf(3));
                  Expression<Double> roundedAvgScore = criteriaBuilder.function(ROUND, Double.class,
                          avgScore.as(BigDecimal.class), criteriaBuilder.literal(2));
                  query.groupBy(root.get("id"),scoreJoin.get(SCORE1),scoreJoin.get(SCORE2),scoreJoin.get(SCORE3))
                          .having(criteriaBuilder.between(roundedAvgScore, down, up));
                  return query.getRestriction();
              };
          }
    }
}
