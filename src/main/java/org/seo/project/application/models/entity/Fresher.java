package org.seo.project.application.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Fresher {
    @Id
    private String id;
    private String name;
    private String address;
    @Column(name = "phone_number")
    private String phone;
    private String email;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "fresher_center",
            uniqueConstraints = @UniqueConstraint(columnNames = {"fresher_id", "center_id"}),
            joinColumns = @JoinColumn(name = "fresher_id"),
            inverseJoinColumns = @JoinColumn(name = "center_id"))
    @JsonIgnore
    private Collection<Center> centers;

    @OneToMany(mappedBy = "fresher", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    Collection<Score> scores;
}
