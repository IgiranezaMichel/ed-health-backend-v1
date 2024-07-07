package com.ed_health_backend_v1.mapper;

import java.util.function.Function;

import com.ed_health_backend_v1.dto.FacultyDTO;
import com.ed_health_backend_v1.modal.Faculty;

public class FacultyMapper implements Function<Faculty,FacultyDTO>{

    @Override
    public FacultyDTO apply(Faculty t) {
      return new FacultyDTO(t.getId().toString(), t.getName(), t.getSchool().getId().toString(), t.getSchool().getName());
    }

}
