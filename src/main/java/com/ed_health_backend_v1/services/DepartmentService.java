package com.ed_health_backend_v1.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ed_health_backend_v1.dto.DepartmentDTO;
import com.ed_health_backend_v1.mapper.DepartmentMapper;
import com.ed_health_backend_v1.modal.Department;
import com.ed_health_backend_v1.modal.Faculty;
import com.ed_health_backend_v1.modal.School;
import com.ed_health_backend_v1.repository.DepartmentRepository;
import com.ed_health_backend_v1.repository.FacultyRepository;
import com.ed_health_backend_v1.repository.SchoolRepositorys;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    SchoolRepositorys schoolRepositorys;
    private DepartmentMapper departmentMapper = new DepartmentMapper();

    public ResponseEntity<String> create(DepartmentDTO departmentDTO) {
        if (departmentDTO.getName().equals(""))
            return new ResponseEntity<>("Department name is required", HttpStatus.BAD_REQUEST);
        if (departmentDTO.getFacultyId().equals(""))
            return new ResponseEntity<>("Faculty is required", HttpStatus.BAD_REQUEST);

        try {
            Faculty faculty = facultyRepository.findById(UUID.fromString(departmentDTO.getFacultyId())).orElseThrow();
            Department department = departmentRepository
                    .save(new Department(departmentDTO.getName(), faculty));
            return new ResponseEntity<>(department.getName() + " added succesful", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Unable to save information check your information and try again",
                    HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> update(DepartmentDTO departmentDTO) {
        if (departmentDTO.getId().equals(""))
            return new ResponseEntity<>("Department is required", HttpStatus.BAD_REQUEST);

        else if (departmentDTO.getName().equals(""))
            return new ResponseEntity<>("Department name is required", HttpStatus.BAD_REQUEST);
        else if (departmentDTO.getFacultyId().equals(""))
            return new ResponseEntity<>("Faculty is required", HttpStatus.BAD_REQUEST);
        try {
            Faculty faculty = facultyRepository.findById(UUID.fromString(departmentDTO.getFacultyId())).orElseThrow();
            Department department = departmentRepository
                    .save(new Department(UUID.fromString(departmentDTO.getId()), departmentDTO.getName(), faculty));
            return new ResponseEntity<>(department.getName() + " added succesful", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Unable to save information check your information and try again",
                    HttpStatus.BAD_REQUEST);
        }
    }

    public List<DepartmentDTO> findSchoolDepartment(String schoolId) {
        School school = schoolRepositorys.findById(UUID.fromString(schoolId)).orElse(null);
        return departmentRepository.findAllByFacultySchool(school).stream().map(departmentMapper).toList();
    }

    public ResponseEntity<String> delete(UUID fromString) {
        try {
            Department department = departmentRepository.findById(fromString).orElseThrow();
            departmentRepository.delete(department);
            return new ResponseEntity<>(department.getName() + " deleted successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Invalid department", HttpStatus.BAD_REQUEST);
        }
    }

    public List<DepartmentDTO> findSchoolDepartmentsFromFaculty(String facultyId) {
        Faculty faculty = facultyRepository.findById(UUID.fromString(facultyId)).orElse(null);
        List<Department> list = departmentRepository.findAllByFaculty(faculty);
        return list.stream().map(departmentMapper).toList();
    }
}
