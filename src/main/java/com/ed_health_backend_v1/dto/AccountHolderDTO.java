package com.ed_health_backend_v1.dto;
import java.util.UUID;
import com.ed_health_backend_v1.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountHolderDTO {
    private UUID id;
    private String name;
    private String gender;
    private String email;
    private String phoneNumber;
    private String photo;
    private String dob;
    @Enumerated(EnumType.STRING)
    private Role role;
    public AccountHolderDTO(String name, String gender, String email, String phoneNumber, String photo, String dob,
            Role role) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.photo = photo;
        this.dob = dob;
        this.role = role;
    }
}
