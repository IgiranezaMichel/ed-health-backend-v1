package com.ed_health_backend_v1.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ed_health_backend_v1.enums.LocationType;
import com.ed_health_backend_v1.modal.Location;
@Repository
public interface LocationRepository extends JpaRepository<Location,UUID> {
    Optional<Location> findByType(String type);
    List<Location> findAllByType(LocationType type);

}

