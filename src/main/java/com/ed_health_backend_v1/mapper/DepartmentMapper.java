package com.ed_health_backend_v1.mapper;

import java.util.function.Function;

import com.ed_health_backend_v1.dto.DepartmentDTO;
import com.ed_health_backend_v1.modal.Department;

public class DepartmentMapper implements Function<Department,DepartmentDTO>{

    @Override
    public DepartmentDTO apply(Department t) {
      return new DepartmentDTO(t.getId().toString(), t.getName(), t.getFaculty().getId().toString(), t.getFaculty().getName());
    }

}
