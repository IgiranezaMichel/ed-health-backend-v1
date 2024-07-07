package com.ed_health_backend_v1.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ed_health_backend_v1.dto.DepartmentDTO;
import com.ed_health_backend_v1.services.DepartmentService;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
  @Autowired private DepartmentService departmentService;
@PostMapping(value = "create")
public ResponseEntity<String>createDepartment(@RequestBody DepartmentDTO Department){
    return departmentService.create(Department);
}
@PostMapping(value = "update")
public ResponseEntity<String>updateDepartment(@RequestBody DepartmentDTO Department){
    return departmentService.update(Department);
}

@GetMapping(value = "all/{schoolId}")
public List<DepartmentDTO> getSchoolDepartments(@PathVariable(name = "schoolId") String schoolId){
    return departmentService.findSchoolDepartment(schoolId);
}
@GetMapping(value = "all/department/{facultyId}")
public List<DepartmentDTO> findDepartmentListBelongsInFaculty(@PathVariable(name = "facultyId") String facultyId){
    return departmentService.findSchoolDepartmentsFromFaculty(facultyId);
}
@DeleteMapping(value = "/{id}")
public  ResponseEntity<String>deleteDepartment(@PathVariable()String id){
    return departmentService.delete(UUID.fromString(id));
}
}
