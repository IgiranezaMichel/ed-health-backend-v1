package com.ed_health_backend_v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ed_health_backend_v1.dto.Pagination;
import com.ed_health_backend_v1.dto.StudentDTO;
import com.ed_health_backend_v1.services.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {
@Autowired private StudentService studentService;
@PostMapping(value = "create")
public ResponseEntity<String> create(@RequestBody()StudentDTO studentDTO){
return studentService.create(studentDTO);
}

@GetMapping(value = "all")
public Pagination<StudentDTO> getAllStudentStudyAtSameSchool(@RequestParam()String schoolId,@RequestParam() String search,@RequestParam(defaultValue = "accountHolder") String sort,@RequestParam(defaultValue = "0") int pageNumber,@RequestParam(defaultValue = "10") int pageSize){
return studentService.getAllStudentStudyAtSameSchool(schoolId,search,sort,pageNumber,pageSize);
}
}
