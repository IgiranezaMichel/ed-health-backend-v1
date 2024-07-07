package com.ed_health_backend_v1.modal;

import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import jakarta.persistence.CascadeType;
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
public class Payment {
    @Id
    @UuidGenerator(style = Style.AUTO)
    private UUID id;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = AccountHolder.class)
    private AccountHolder accountHolder;
    private long amount;
    private String currency;
    private String paymentCode;
    private Date timeStamp;
    
}
