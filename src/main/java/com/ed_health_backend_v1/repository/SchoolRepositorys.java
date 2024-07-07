package com.ed_health_backend_v1.repository;

import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ed_health_backend_v1.modal.School;

@Repository
public interface SchoolRepositorys extends JpaRepository<School,UUID> {
    Page<School> findAllByNameContainingIgnoreCase(String search, PageRequest of);
}
