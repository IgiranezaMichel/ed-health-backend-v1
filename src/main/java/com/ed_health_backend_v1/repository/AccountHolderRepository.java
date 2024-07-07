package com.ed_health_backend_v1.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ed_health_backend_v1.modal.AccountHolder;

public interface AccountHolderRepository extends JpaRepository<AccountHolder,UUID>{
    Page<AccountHolder> findAllByNameContainingIgnoreCase(String search, PageRequest of);
    AccountHolder findByEmail(String email);
    boolean existsByEmail(String email);
}
