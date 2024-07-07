package com.ed_health_backend_v1.services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ed_health_backend_v1.dto.ControlPanelDTO;
import com.ed_health_backend_v1.mapper.ControlPanelMapper;
import com.ed_health_backend_v1.modal.AccountHolder;
import com.ed_health_backend_v1.modal.ControlPanel;
import com.ed_health_backend_v1.repository.ControlPanelRepository;

@Service
public class ControlPanelServices {
  @Autowired
  private ControlPanelRepository controlPanelRepository;
  @Autowired
  private AccountHolderServices accountHolderServices;
  private ControlPanelMapper controlPanelMapper = new ControlPanelMapper();

  public ResponseEntity<String> create(ControlPanelDTO controlPanelDTO) {
    if (controlPanelDTO.getAccountHolderEmail().length() == 0) {
      return new ResponseEntity<>("Please specify a correct user", HttpStatus.BAD_REQUEST);
    }
    ControlPanel controlPanel = new ControlPanel();
    AccountHolder accountHolder = accountHolderServices.findByEmail(controlPanelDTO.getAccountHolderEmail());
    controlPanel = controlPanelRepository.findByAccountHolder(accountHolder);
    if (controlPanel != null) {
      controlPanel = controlPanelRepository
          .save(new ControlPanel(controlPanel.getId(), accountHolder, UUID.fromString(controlPanelDTO.getFk())));
    } else {
      controlPanel = controlPanelRepository
          .save(new ControlPanel(accountHolder, UUID.fromString(controlPanelDTO.getFk())));
    }
    return new ResponseEntity<>("well done", HttpStatus.CREATED);
  }

  public List<ControlPanelDTO> findAllByFk(String fk) {
    return controlPanelRepository.findAllByFk(UUID.fromString(fk)).stream().map(controlPanelMapper).toList();
  }
}
