package org.seo.project.application.repositories;

import org.seo.project.application.models.entity.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CenterRepository extends JpaRepository<Center, String> {
}
