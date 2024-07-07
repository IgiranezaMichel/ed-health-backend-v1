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

import com.ed_health_backend_v1.dto.SchoolDTO;
import com.ed_health_backend_v1.dto.Pagination;
import com.ed_health_backend_v1.services.SchoolServices;

@RestController
@RequestMapping("/api/school")
public class SchoolController {
@Autowired private SchoolServices schoolServices;
@PostMapping(value = "create/{email}")
public ResponseEntity<String>createSchool(@RequestBody SchoolDTO school,@PathVariable(name = "email")String accountHolderEmail){
    return schoolServices.create(school,accountHolderEmail);
}
@PostMapping(value = "update")
public ResponseEntity<String>updateSchool(@RequestBody SchoolDTO School){
    return schoolServices.update(School);
}
@GetMapping(value = "all")
public Pagination<SchoolDTO>SchoolList(@RequestParam() String search,@RequestParam(defaultValue = "name") String sort,@RequestParam(defaultValue = "0") int pageNumber,@RequestParam(defaultValue = "10") int pageSize){
    return schoolServices.getAllSchool(search,sort,pageNumber,pageSize);
}
@GetMapping(value = "/{id}")
public SchoolDTO findSchoolById(@PathVariable(name = "id") String SchoolId){
    return schoolServices.findSchoolById(SchoolId);
}
@DeleteMapping(value = "/{id}")
public  ResponseEntity<String>deleteSchool(@PathVariable()String id){
    return schoolServices.delete(UUID.fromString(id));
}
}
