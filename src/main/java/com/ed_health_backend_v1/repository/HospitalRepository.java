package com.ed_health_backend_v1.repository;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ed_health_backend_v1.modal.Hospital;
@Repository
public interface HospitalRepository extends JpaRepository<Hospital,UUID> {
    Page<Hospital> findAllByName(String search, PageRequest of);
    Page<Hospital> findAllByNameContainingIgnoreCase(String search, PageRequest of);

}

