package org.seo.project.application.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Collection;

@Setter @Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Center {
    @Id
    private String id;
    private String name;
    private String address;
    @Column(name = "phone_number")
    private String phone;
    @ManyToMany(mappedBy = "centers")
    @JsonIgnore
    private Collection<Fresher> freshers;
}
