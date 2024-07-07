package com.ed_health_backend_v1.modal;

import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;

import com.ed_health_backend_v1.enums.LocationType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    @Id
    @UuidGenerator(style = Style.AUTO)
    private UUID id;
    private String name;
    @Enumerated(EnumType.STRING)
    private LocationType type;
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Location.class, optional = true)
    private Location location;

    public Location(UUID id, String name, LocationType type, Location location) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.location = location;
    }
    public Location(String name, LocationType type, Location location) {
        this.name = name;
        this.type = type;
        this.location = location;
    }
    public Location(String name, LocationType type) {
        this.name = name;
        this.type = type;
    }
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "location", targetEntity = Location.class)
    private List<Location> locations;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "location", targetEntity = School.class)
    private List<School> schools;
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, targetEntity = Hospital.class)
    private List<Hospital> hospitals;
}
