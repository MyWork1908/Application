package org.seo.project.application.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"fresher_id", "subject_id"})
})
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    private Double score01;
    private Double score02;
    private Double score03;
    @ManyToOne()
    @JoinColumn(name = "fresher_id")
    @JsonIgnore
    Fresher fresher;

    @ManyToOne()
    @JoinColumn(name = "subject_id")
    @JsonIgnore
    Subject subject;
}
