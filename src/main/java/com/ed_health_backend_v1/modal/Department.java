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
public class Department {
@Id
@UuidGenerator(style = Style.AUTO)
private UUID id;
private String name;
@ManyToOne(cascade = CascadeType.ALL,targetEntity = Faculty.class)
private Faculty faculty;
public Department(UUID id, String name, Faculty faculty) {
    this.id = id;
    this.name = name;
    this.faculty = faculty;
}

public Department(String name, Faculty faculty) {
    this.name = name;
    this.faculty = faculty;
}

@OneToMany(mappedBy = "department",cascade = CascadeType.ALL,targetEntity = Student.class)
private List<Student>students;


}
