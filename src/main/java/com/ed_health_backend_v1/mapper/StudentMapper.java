package com.ed_health_backend_v1.mapper;

import java.util.function.Function;

import com.ed_health_backend_v1.dto.StudentDTO;
import com.ed_health_backend_v1.modal.Student;

public class StudentMapper implements Function<Student,StudentDTO>{

    @Override
    public StudentDTO apply(Student t) {
       return new StudentDTO(t.getId(), t.getAccountHolder(), t.getDepartment(), t.getSchool());
    }

}
