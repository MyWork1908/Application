package org.seo.project.application.repositories;

import org.seo.project.application.models.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long>, JpaSpecificationExecutor<Score> {
    class ScoreSpecification {
        private ScoreSpecification() {}

    }
}
