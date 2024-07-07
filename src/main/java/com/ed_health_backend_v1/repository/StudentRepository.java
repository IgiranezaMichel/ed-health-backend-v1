package com.ed_health_backend_v1.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ed_health_backend_v1.modal.School;
import com.ed_health_backend_v1.modal.Student;

public interface StudentRepository extends JpaRepository<Student,UUID>{
    Page<Student> findAllBySchoolAndAccountHolderNameContainingIgnoreCase(School school, String search, PageRequest of);
    Page<Student> findAllBySchool(School school, PageRequest of);
}
