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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ed_health_backend_v1.dto.FacultyDTO;
import com.ed_health_backend_v1.dto.Pagination;
import com.ed_health_backend_v1.services.FacultyService;
@RestController
@RequestMapping("/api/faculty")
public class FacultyController {
    @Autowired private FacultyService facultyService;
@PostMapping(value = "create")
public ResponseEntity<String>createFaculty(@RequestBody FacultyDTO Faculty){
    return facultyService.create(Faculty);
}
@PostMapping(value = "update")
public ResponseEntity<String>updateFaculty(@RequestBody FacultyDTO Faculty){
    return facultyService.update(Faculty);
}
@GetMapping(value = "all")
public Pagination<FacultyDTO>FacultyList(@RequestParam() String search,@RequestParam() String schoolId,@RequestParam(defaultValue = "name") String sort,@RequestParam(defaultValue = "0") int pageNumber,@RequestParam(defaultValue = "10") int pageSize){
    return facultyService.getAllFacultyFromSameSchool(search,schoolId,sort,pageNumber,pageSize);
}
@GetMapping(value = "school")
public List<FacultyDTO> getSchoolFacultyies(@RequestParam(name = "schoolId") String schoolId){
    return facultyService.findSchoolFaculty(schoolId);
}
@GetMapping(value = "faculty/{id}")
public FacultyDTO findFacultyById(@PathVariable(name = "facultyId") String facultyId){
    return facultyService.findFacultyById(facultyId);
}
@DeleteMapping(value = "/{id}")
public  ResponseEntity<String>deleteFaculty(@PathVariable()String id){
    return facultyService.delete(UUID.fromString(id));
}
}
