package com.ed_health_backend_v1.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ed_health_backend_v1.modal.AccountHolder;
import com.ed_health_backend_v1.modal.ControlPanel;
@Repository
public interface ControlPanelRepository extends JpaRepository<ControlPanel,UUID> {
    ControlPanel findByAccountHolder(AccountHolder accountHolder);
    List<ControlPanel> findAllByFk(UUID fromString);
}
