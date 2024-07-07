package com.ed_health_backend_v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ed_health_backend_v1.dto.ControlPanelDTO;
import com.ed_health_backend_v1.services.ControlPanelServices;

@RestController
@RequestMapping("/api/controlPanel")
public class ControlPanelController {
@Autowired private ControlPanelServices controlPanelServices;
@GetMapping("findSchoolAdmins/{fk}")
public List<ControlPanelDTO>getAllAdminByFk(@PathVariable String fk){
return controlPanelServices.findAllByFk(fk);
}
}
