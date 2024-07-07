package com.ed_health_backend_v1.modal;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import com.ed_health_backend_v1.enums.StudentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @UuidGenerator(style = Style.AUTO)
    private UUID id;
    @OneToOne(cascade=CascadeType.ALL,targetEntity = AccountHolder.class)
    private AccountHolder accountHolder;
    @Enumerated(EnumType.STRING)
    private StudentStatus status;
    @ManyToOne(cascade = CascadeType.ALL,targetEntity = Department.class)
    private Department department;
    @ManyToOne(cascade = CascadeType.ALL,targetEntity = School.class)
    private School school;
    public Student(AccountHolder accountHolder, StudentStatus status, Department department, School school) {
        this.accountHolder = accountHolder;
        this.status = status;
        this.department = department;
        this.school = school;
    }
}
