package com.ed_health_backend_v1.modal;

import java.util.Base64;
import java.util.List;
import java.util.UUID;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class School {
    @UuidGenerator(style = Style.AUTO)
    @Id
    private UUID id;
    private String name;
    @Lob
    private byte[] logo;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Location.class)
    private Location location;
    @OneToMany(mappedBy = "school", targetEntity = Faculty.class, cascade = CascadeType.ALL)
    private List<Faculty> faculties;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "school",targetEntity = Student.class)
    private List<Student>students;
    public School(String name, String logo, Location location) {
        this.name = name;
        this.logo = Base64.getDecoder().decode(logo);
        this.location = location;
    }
    public School(UUID id, String name, String logo) {
        this.id = id;
        this.name = name;
        this.logo = Base64.getDecoder().decode(logo);
    }

    public School(UUID id, String name, String logo, Location location) {
        this.id = id;
        this.name = name;
        this.logo = Base64.getDecoder().decode(logo);
        this.location = location;
    }
}
