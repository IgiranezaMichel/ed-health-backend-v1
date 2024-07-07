package com.ed_health_backend_v1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacultyDTO {
    private String id;
    private String name;
    private String schoolId;
    private String schoolName;
}
