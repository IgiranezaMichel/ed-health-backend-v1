package com.ed_health_backend_v1.modal;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Job {
    @Id
    @UuidGenerator(style = Style.AUTO)
    private UUID id;
    private String title;
    @Column(columnDefinition = "text")
    private String description;
    private LocalDate deadline;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Hospital.class)
    private Hospital hospital;

}
