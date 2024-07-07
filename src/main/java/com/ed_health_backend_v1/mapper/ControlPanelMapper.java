package com.ed_health_backend_v1.mapper;

import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.function.Function;

import com.ed_health_backend_v1.dto.ControlPanelDTO;
import com.ed_health_backend_v1.modal.ControlPanel;

public class ControlPanelMapper implements Function<ControlPanel,ControlPanelDTO>{

    @Override
    public ControlPanelDTO apply(ControlPanel t) {
        DateTimeFormatter formatter =DateTimeFormatter.ofPattern("dd-MMMM-yyy");
       return new ControlPanelDTO(t.getId().toString(),t.getAccountHolder().getEmail(), t.getAccountHolder().getPhoneNumber(),
       t.getAccountHolder().getName(),"data:image/png;base64,"+Base64.getEncoder().encodeToString(t.getAccountHolder().getPhoto()), t.getAccountHolder().getGender(), formatter.format(t.getAccountHolder().getDob()));
    }

}
