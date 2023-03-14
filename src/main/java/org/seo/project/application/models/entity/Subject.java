package org.seo.project.application.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Subject {
    @Id
    private String id;
    @Column(name = "language_programming", unique = true)
    private String lp;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    Collection<Score> scores;
}
