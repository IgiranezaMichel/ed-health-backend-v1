package com.ed_health_backend_v1.controller;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ed_health_backend_v1.dto.HospitalDTO;
import com.ed_health_backend_v1.dto.Pagination;
import com.ed_health_backend_v1.services.HospitalServices;

@RestController
@RequestMapping("/api/hospital")
public class HospitalController {
    @Autowired private HospitalServices hospitalServices;
@PostMapping(value = "create")
public ResponseEntity<String>createHospital(@RequestBody HospitalDTO hospital){
    return hospitalServices.create(hospital);
}
@PostMapping(value = "update")
public ResponseEntity<String>updateHospital(@RequestBody HospitalDTO hospital){
    return hospitalServices.update(hospital);
}
@GetMapping(value = "all")
public Pagination<HospitalDTO>hospitalList(@RequestParam() String search,@RequestParam(defaultValue = "name") String sort,@RequestParam(defaultValue = "0") int pageNumber,@RequestParam(defaultValue = "10") int pageSize){
    return hospitalServices.getAllHospital(search,sort,pageNumber,pageSize);
}
@GetMapping(value = "/{id}")
public HospitalDTO findHospitalById(@PathVariable(name = "id") String hospitalId){
    return hospitalServices.findHospitalById(hospitalId);
}
@DeleteMapping(value = "/{id}")
public  ResponseEntity<String>deleteHospital(@PathVariable()String id){
    return hospitalServices.delete(UUID.fromString(id));
}
}
