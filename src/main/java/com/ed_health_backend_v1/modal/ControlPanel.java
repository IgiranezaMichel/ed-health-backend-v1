package com.ed_health_backend_v1.modal;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

// import jakarta.persistence.CascadeType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.CascadeType;
// import jakarta.persistence.OneToOne;
import jakarta.persistence.Entity;
// import jakarta.persistence.FetchType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ControlPanel {
    @Id
    @UuidGenerator(style = Style.AUTO)
    private UUID id;
    @OneToOne(cascade = CascadeType.ALL, targetEntity = AccountHolder.class)
    private AccountHolder accountHolder;
    private UUID fk;
    public ControlPanel(AccountHolder accountHolder,UUID fk) {
        this.accountHolder = accountHolder;
        this.fk=fk;
    }
}
