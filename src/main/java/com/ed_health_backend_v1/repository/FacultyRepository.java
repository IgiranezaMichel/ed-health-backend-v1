package com.ed_health_backend_v1.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ed_health_backend_v1.modal.Faculty;
import com.ed_health_backend_v1.modal.School;
@Repository
public interface FacultyRepository extends JpaRepository<Faculty,UUID>{
    List<Faculty> findAllBySchool(School school);
    Page<Faculty> findAllByNameContainingIgnoreCase(String search, PageRequest of);
    Page<Faculty> findAllBySchool(School school,PageRequest of);
    Page<Faculty> findAllBySchoolAndNameContainingIgnoreCase(School school, String search, PageRequest of);

}
