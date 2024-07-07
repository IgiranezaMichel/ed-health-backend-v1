package com.ed_health_backend_v1.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ed_health_backend_v1.dto.LocationDTO;
import com.ed_health_backend_v1.enums.LocationType;
import com.ed_health_backend_v1.mapper.LocationMapper;
import com.ed_health_backend_v1.modal.Location;
import com.ed_health_backend_v1.repository.LocationRepository;
@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;
    private LocationMapper locationMapper = new LocationMapper();

    public ResponseEntity<String> create(LocationDTO locationDTO, UUID locationFk) {
        Location location = new Location();
        if (locationDTO.getId() == null && locationFk == null) {
            location = locationRepository.save(new Location(locationDTO.getName(), locationDTO.getType()));
        } else if (locationFk != null)
            location = locationRepository.save(new Location(locationDTO.getId(), locationDTO.getName(),
                    locationDTO.getType(), this.findLocationById(locationFk)));
        return new ResponseEntity<>(location.getName() + " saved", HttpStatus.CREATED);
    }

    public Location findLocationById(UUID id) {
        return locationRepository.findById(id).orElse(null);
    }
    public List<LocationDTO> findLocationByType(LocationType type) {
        return locationRepository.findAllByType(type).stream().map(locationMapper).collect(Collectors.toList());
    }
    public List<LocationDTO> locationList() {
        return locationRepository.findAll().stream().map(locationMapper).collect(Collectors.toList());
    }

    public List<LocationDTO> findLocationByLocation(UUID location) {
        return findLocationById(location).getLocations().stream()
                    .map(locationMapper).toList();
}
}
