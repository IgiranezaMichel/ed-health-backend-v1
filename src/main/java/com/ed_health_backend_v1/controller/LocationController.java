package com.ed_health_backend_v1.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ed_health_backend_v1.dto.LocationDTO;
import com.ed_health_backend_v1.enums.LocationType;
import com.ed_health_backend_v1.services.LocationService;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/location")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @PostMapping()
    public ResponseEntity<String> createLocation(@RequestParam(required = false) String locationFk,
            @RequestBody() LocationDTO locationDTO) {
        UUID location = null;
        if (!locationFk.equals(""))
            location = UUID.fromString(locationFk);
        return locationService.create(locationDTO, location);
    }

    @GetMapping(value = "all")
    public List<LocationDTO> getAll() {
        return locationService.locationList();
    }

    @GetMapping(value = "type")
    public List<LocationDTO> findLocationByType(@RequestParam() LocationType locationType) {
        return locationService.findLocationByType(locationType);
    }

    @GetMapping(value = "all/fk")
    public List<LocationDTO> getAllLocationFk(@RequestParam() String location) {
        return locationService.findLocationByLocation(UUID.fromString(location));
    }
}
