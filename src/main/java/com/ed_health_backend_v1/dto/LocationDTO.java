package com.ed_health_backend_v1.dto;

import java.util.UUID;

import com.ed_health_backend_v1.enums.LocationType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationDTO {
    private UUID id;
    private String name;
    private LocationType type;
}
