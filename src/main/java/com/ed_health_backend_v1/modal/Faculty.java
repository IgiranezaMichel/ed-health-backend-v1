package com.ed_health_backend_v1.modal;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Faculty {
    @Id
    @UuidGenerator(style = Style.AUTO)
    private UUID id;
    private String name;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = School.class)
    private School school;
    
    public Faculty(String name, School school) {
        this.name = name;
        this.school = school;
    }
    public Faculty(UUID id, String name, School school) {
        this.id = id;
        this.name = name;
        this.school = school;
    }

    @OneToMany(cascade = CascadeType.ALL,targetEntity = Department.class,mappedBy = "faculty")
    private List<Department> department;
}
