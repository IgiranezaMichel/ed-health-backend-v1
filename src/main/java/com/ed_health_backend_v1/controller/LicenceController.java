package com.ed_health_backend_v1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ed_health_backend_v1.dto.LicenceDTO;
import com.ed_health_backend_v1.dto.Pagination;
import com.ed_health_backend_v1.services.LicenceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/licence")
public class LicenceController {
    @Autowired
    private LicenceServices licenceServices;

    @PostMapping(value = "create")
    public ResponseEntity<String> createLicence(@RequestBody LicenceDTO licence) {
        return licenceServices.create(licence);
    }

    @PostMapping(value = "update")
    public ResponseEntity<String> updateLicence(@RequestBody LicenceDTO licence) {
        return licenceServices.update(licence);
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<String> deleteLicence(@PathVariable String id) {
        return licenceServices.delete(id);
    }

    @GetMapping(value = "/{id}")
    public LicenceDTO findLicenceById(@PathVariable String id) {
        return licenceServices.findLicenceById(id);
    }

    @GetMapping(value = "all")
    public Pagination<LicenceDTO> hospitalList(@RequestParam() String search,
            @RequestParam(defaultValue = "timeStamp") String sort, @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        return licenceServices.getAllLicence(search, sort, pageNumber, pageSize);
    }
}
