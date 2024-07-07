package com.ed_health_backend_v1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ControlPanelDTO {
    private String id;
    private String accountHolderEmail;
    private String fk;
    
    public ControlPanelDTO(String accountHolderEmail, String fk) {
        this.accountHolderEmail = accountHolderEmail;
        this.fk = fk;
    }
    public ControlPanelDTO(String id, String accountHolderEmail,String fk) {
        this.id = id;
        this.accountHolderEmail = accountHolderEmail;
        this.fk=fk;
    }
    private String accountHolderPhoneNumber;
    private String accountHolderName;
    private String accountHolderPicture;
    private String accountHolderGender;
    private String dob;

    public ControlPanelDTO(String id, String accountHolderEmail, String accountHolderPhoneNumber,
            String accountHolderName, String accountHolderPicture, String accountHolderGender, String dob) {
        this.id = id;
        this.accountHolderEmail = accountHolderEmail;
        this.accountHolderPhoneNumber = accountHolderPhoneNumber;
        this.accountHolderName = accountHolderName;
        this.accountHolderPicture = accountHolderPicture;
        this.accountHolderGender = accountHolderGender;
        this.dob = dob;
    }
}
