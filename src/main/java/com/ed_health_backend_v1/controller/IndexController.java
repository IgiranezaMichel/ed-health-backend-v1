package com.ed_health_backend_v1.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ed_health_backend_v1.dto.AccountHolderDTO;
import com.ed_health_backend_v1.enums.Role;
import com.ed_health_backend_v1.modal.AccountHolder;
import com.ed_health_backend_v1.repository.AccountHolderRepository;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class IndexController {
    @Autowired
    private AccountHolderRepository accountHolderRepository;

    @RequestMapping("/login")
    public String requestMethodName() {
        AccountHolder accountHolder = new AccountHolder("admin", "Male", "admin@gmail.com", "07883256", null,
                LocalDate.now(), BCrypt.hashpw("admin", BCrypt.gensalt()), Role.ROLE_ADMIN);
        // AccountHolder result=accountHolderRepository.save(accountHolder);
        return accountHolder.getName();
    }

    @RequestMapping("/login-success")
    public Role successLogin(Principal principal) {
        AccountHolder accountHolder = accountHolderRepository.findByEmail(principal.getName());
        AccountHolder result = accountHolderRepository.save(accountHolder);
        return result.getRole();
    }

    @GetMapping("/checkSession")
    public AccountHolderDTO checkSession(Principal principal) {
        AccountHolder result = accountHolderRepository.findByEmail(principal.getName());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyy");
        String base64Img = "";
        if (result.getPhoto() != null)
            base64Img = "data:image/png;base64," + Base64.getEncoder().encodeToString(result.getPhoto());
        return new AccountHolderDTO(result.getName(), result.getGender(), result.getEmail(), result.getPhoneNumber(),
                base64Img,
                formatter.format(result.getDob()), result.getRole());
    }
}
