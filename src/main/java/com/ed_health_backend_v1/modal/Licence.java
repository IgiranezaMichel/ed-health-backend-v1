package com.ed_health_backend_v1.modal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.UUID;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Licence {
    @Id
    @UuidGenerator(style = Style.AUTO)
    private UUID id;
    private String licenceYear;
    @Enumerated(EnumType.STRING)
    private Month cohortMonth;
    private LocalDate deadline;
    private LocalDateTime timeStamp;
    public Licence(String licenceYear, Month cohortMonth, LocalDate deadline, LocalDateTime timeStamp) {
        this.licenceYear = licenceYear;
        this.cohortMonth = cohortMonth;
        this.deadline = deadline;
        this.timeStamp = timeStamp;
    }
    
}
