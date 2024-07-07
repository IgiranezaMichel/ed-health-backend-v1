package com.ed_health_backend_v1.modal;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;
import com.ed_health_backend_v1.enums.Role;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountHolder {
    @Id
    @UuidGenerator(style = Style.AUTO)
    private UUID id;
    private String name;
    private String gender;
    @Column(unique = true)
    private String email;
    private String phoneNumber;
    @Lob
    private byte[] photo;
    private LocalDate dob;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    
    public AccountHolder(String name, String gender, String email, String phoneNumber, byte[] photo, LocalDate dob,
            String password, Role role) {
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.photo = photo;
        this.dob = dob;
        this.password = password;
        this.role = role;
    }

    //
    @OneToOne(cascade = CascadeType.ALL, targetEntity = Student.class, mappedBy = "accountHolder")
    private Student student;

    public AccountHolder(UUID id, String name, String gender, String email, String phoneNumber, byte[] photo,
            LocalDate dob, String password, Role role) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.photo = photo;
        this.dob = dob;
        this.password = password;
        this.role = role;
    }

    public AccountHolder(UUID id, String name, String gender, String email, String phoneNumber, byte[] photo,
            LocalDate dob, String password, Role role, ControlPanel controlPanel) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.photo = photo;
        this.dob = dob;
        this.password = password;
        this.role = role;
        this.controlPanel = controlPanel;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = ControlPanel.class, mappedBy = "accountHolder")
    private ControlPanel controlPanel;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accountHolder", targetEntity = Payment.class)
    private List<Payment> payments;
    // @ManyToMany(cascade = CascadeType.ALL,mappedBy = "accountHolders")
    // private List<School>schools;
}
