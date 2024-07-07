package com.ed_health_backend_v1.mapper;

import java.util.function.Function;

import com.ed_health_backend_v1.dto.LocationDTO;
import com.ed_health_backend_v1.modal.Location;

public  class LocationMapper implements Function<Location,LocationDTO> {
    @Override
    public LocationDTO apply(Location t) {
        return new LocationDTO(t.getId(), t.getName(), t.getType());
    }

}
