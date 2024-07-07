package com.ed_health_backend_v1.dto;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospitalDTO {
    private String id;
    private String name;
    @Lob
    private String logo;
    private String location;
    private String province;
    private String district;
    private String sector;
    public HospitalDTO(String id, String name, String logo, String province, String district, String sector) {
        this.id = id;
        this.name = name;
        this.logo = logo;
        this.province = province;
        this.district = district;
        this.sector = sector;
    }
    public HospitalDTO(String id, String name, String logo,String location) {
        this.id = id;
        this.name = name;
        this.logo = logo;
        this.location=location;
    }
}
