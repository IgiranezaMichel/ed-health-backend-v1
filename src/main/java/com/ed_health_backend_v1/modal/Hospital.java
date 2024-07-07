package com.ed_health_backend_v1.modal;

import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;
import org.hibernate.annotations.UuidGenerator.Style;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hospital {
    @Id
    @UuidGenerator(style = Style.AUTO)
    private UUID id;
    private String name;
    @Lob
    private byte[] logo;
    @ManyToOne(cascade = CascadeType.ALL,targetEntity = Location.class)
    private Location location;
    
    public Hospital(String name, String logo, Location location) {
        this.name = name;
        this.logo = Base64.getDecoder().decode(logo);
        this.location = location;
    }
    public Hospital(UUID id, String name, String logo, Location location) {
        this.id = id;
        this.name = name;
        this.logo = Base64.getDecoder().decode(logo);
        this.location = location;
    }
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "hospital", targetEntity = Job.class)
    private List<Job> jobList;
}
