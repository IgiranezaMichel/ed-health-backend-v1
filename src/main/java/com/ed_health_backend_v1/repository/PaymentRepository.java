package com.ed_health_backend_v1.repository;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ed_health_backend_v1.modal.Payment;
@Repository
public interface PaymentRepository extends JpaRepository<Payment,UUID> {

}

