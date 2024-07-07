package com.ed_health_backend_v1.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ed_health_backend_v1.modal.Licence;
@Repository
public interface LicenceRepository extends JpaRepository<Licence,UUID> {
    Page<Licence> findAllByLicenceYearContainingIgnoreCase(String search, PageRequest of);

}

