package com.ed_health_backend_v1.mapper;

import java.time.format.DateTimeFormatter;
import java.util.function.Function;
import com.ed_health_backend_v1.dto.LicenceDTO;
import com.ed_health_backend_v1.modal.Licence;

public class LicenceMapper implements Function<Licence,LicenceDTO>{

    @Override
    public LicenceDTO apply(Licence t) {
        DateTimeFormatter formatter =DateTimeFormatter.ofPattern("dd-MMMM-yyy");
        DateTimeFormatter formatter2 =DateTimeFormatter.ofPattern("dd-MMMM-yyy MM:ss a");
      return new LicenceDTO(t.getId().toString(), t.getLicenceYear(), t.getCohortMonth(), formatter.format(t.getDeadline()), formatter2.format(t.getTimeStamp()));
    }

}
