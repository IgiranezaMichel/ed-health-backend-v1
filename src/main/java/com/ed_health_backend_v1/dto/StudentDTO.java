package com.ed_health_backend_v1.dto;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.UUID;

import com.ed_health_backend_v1.enums.StudentStatus;
import com.ed_health_backend_v1.modal.AccountHolder;
import com.ed_health_backend_v1.modal.Department;
import com.ed_health_backend_v1.modal.School;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDTO {
    private String id;
    private String name;
    private String gender;
    private String dateOfBirth;
    private String phoneNumber;
    private String profilePicture;
    private String email;
    private StudentStatus status;
    private String departmentId;
    private String departmentName;
    private String facultyName;
    private String schoolId;
    private String schoolName;
    private String schoolProvince;
    private String schoolDistrict;
    private String schoolSector;
    public StudentDTO(UUID id,AccountHolder accountHolder,Department department,School school) {
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd-MMMM-yyy");
        
        this.id = id.toString();
        this.name = accountHolder.getName();
        this.dateOfBirth = formatter.format(accountHolder.getDob());
        this.phoneNumber = accountHolder.getPhoneNumber();
        this.gender = accountHolder.getEmail();
        this.profilePicture = "data:image/png;base64,"+Base64.getEncoder().encodeToString(accountHolder.getPhoto());
        this.departmentId = department.getId().toString();
        this.facultyName = department.getFaculty().getName();
        this.schoolName = school.getName();
        this.schoolProvince = school.getLocation().getName();
        this.schoolDistrict = school.getLocation().getLocation().getName();
        this.schoolSector = school.getLocation().getLocation().getLocation().getName();
    }
}