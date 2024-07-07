package com.ed_health_backend_v1.mapper;

import java.util.Base64;
import java.util.function.Function;

import com.ed_health_backend_v1.dto.HospitalDTO;
import com.ed_health_backend_v1.modal.Hospital;

public class HospitalMapper implements Function<Hospital,HospitalDTO>{

    @Override
    public HospitalDTO apply(Hospital t) {
      return new HospitalDTO(t.getId().toString(), t.getName(), "data:image/png;base64,"+Base64.getEncoder().encodeToString(t.getLogo()), t.getLocation().getLocation().getLocation().getName(), t.getLocation().getLocation().getName(), t.getLocation().getName());
    }

}
