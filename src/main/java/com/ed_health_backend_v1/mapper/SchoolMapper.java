package com.ed_health_backend_v1.mapper;

import java.util.Base64;
import java.util.function.Function;
import com.ed_health_backend_v1.dto.SchoolDTO;
import com.ed_health_backend_v1.modal.School;

public class SchoolMapper implements Function<School,SchoolDTO>{

    @Override
    public SchoolDTO apply(School t) {
      return new SchoolDTO(t.getId().toString(), t.getName(), "data:image/png;base64,"+Base64.getEncoder().encodeToString(t.getLogo()),t.getLocation().getLocation().getLocation().getName(), t.getLocation().getLocation().getName(), t.getLocation().getName());
    }
}
