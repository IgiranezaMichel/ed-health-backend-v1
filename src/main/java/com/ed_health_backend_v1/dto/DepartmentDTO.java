package com.ed_health_backend_v1.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
private String id;
private String name;
private String facultyId;
private String facultyName;
}
