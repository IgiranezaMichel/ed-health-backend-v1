package com.ed_health_backend_v1.dto;

import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobDTO {
private UUID id;
    private String title;
    private String description;
    private LocalDate deadline;
    public JobDTO(UUID id, String title, String description, LocalDate deadline, String hospitalName) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.hospitalName = hospitalName;
    }
    private String hospitalName;
    private String hospitalProvince;
    private String hospitalDistrict;
    private String hospitalSector;

}
