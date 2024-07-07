package com.ed_health_backend_v1.services;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.ed_health_backend_v1.dto.AccountHolderDTO;
import com.ed_health_backend_v1.dto.Pagination;
import com.ed_health_backend_v1.mapper.AccountHolderMapper;
import com.ed_health_backend_v1.modal.AccountHolder;
import com.ed_health_backend_v1.repository.AccountHolderRepository;
import com.ed_health_backend_v1.tools.RandomPassword;

@Service
public class AccountHolderServices {
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    private AccountHolderMapper accountHolderMapper = new AccountHolderMapper();
    private RandomPassword randomPassword = new RandomPassword();
    public ResponseEntity<String> create(AccountHolderDTO accountHolderDTO, String password) {
        AccountHolder accountHolder = new AccountHolder();
        if (accountHolderDTO.getDob().equals(""))
            return new ResponseEntity<>("date of birth is required", HttpStatus.SEE_OTHER);
        if (accountHolderDTO.getEmail().equals(""))
            return new ResponseEntity<>("Email is required", HttpStatus.SEE_OTHER);
        else if (accountHolderDTO.getPhoto().length() == 0)
            return new ResponseEntity<>("Profile picture is required", HttpStatus.SEE_OTHER);
        else if (accountHolderDTO.getName().equals(""))
            return new ResponseEntity<>("Name is required", HttpStatus.SEE_OTHER);
        if (password.equals("")) {
            password = randomPassword.randomPassword();
        }
        if(accountHolderRepository.existsByEmail(accountHolderDTO.getEmail()))return new ResponseEntity<>("User already exist",HttpStatus.SEE_OTHER);
            accountHolder = accountHolderRepository.save(new AccountHolder(accountHolderDTO.getId(),
                    accountHolderDTO.getName(), accountHolderDTO.getGender(), accountHolderDTO.getEmail(),
                    accountHolderDTO.getPhoneNumber(), Base64.getDecoder().decode(accountHolderDTO.getPhoto().split("base64,")[1]),
                    LocalDate.parse(accountHolderDTO.getDob()), password, accountHolderDTO.getRole()));
        return new ResponseEntity<>(accountHolder.getName() + " added succesful", HttpStatus.CREATED);
    }
    public AccountHolder createAccount(AccountHolderDTO accountHolderDTO, String password) {
        AccountHolder accountHolder = new AccountHolder();
        if (accountHolderDTO.getDob().equals(""))
             new Exception("date of birth is required");
        if (accountHolderDTO.getEmail().equals(""))
             new Exception("Email is required");
        else if (accountHolderDTO.getPhoto().length() == 0)
             new Exception("Profile picture is required");
        else if (accountHolderDTO.getName().equals(""))
             new Exception("Name is required");
        if (password==null||(password.length()==0)) {
            password = randomPassword.randomPassword();
        }
            accountHolder = accountHolderRepository.save(new AccountHolder(accountHolderDTO.getId(),
                    accountHolderDTO.getName(), accountHolderDTO.getGender(), accountHolderDTO.getEmail(),
                    accountHolderDTO.getPhoneNumber(), Base64.getDecoder().decode(accountHolderDTO.getPhoto().split("base64,")[1]),
                    LocalDate.parse(accountHolderDTO.getDob()), password, accountHolderDTO.getRole()));
        return accountHolder;
    }
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Pagination<AccountHolderDTO> getAllAccountHolder(String search, String sort, int pageNumber, int pageSize) {
        if (search.length() == 0) {
            Page<AccountHolder> page = accountHolderRepository
                    .findAll(PageRequest.of(pageNumber, pageSize, Sort.by(sort)));
            List<AccountHolderDTO> list = page.getContent().stream().map(accountHolderMapper).toList();
            return new Pagination(list, page.getNumber(), page.getTotalPages());
        } else {
            Page<AccountHolder> page = accountHolderRepository.findAllByNameContainingIgnoreCase(search,
                    PageRequest.of(pageNumber, pageSize, Sort.by(sort)));
            List<AccountHolderDTO> list = page.getContent().stream().map(accountHolderMapper).toList();
            return new Pagination(list, page.getNumber(), page.getTotalPages());
        }
    }

    public ResponseEntity<String> delete(UUID id) {
        try {
            AccountHolder accountHolder = this.findAccountHolder(id);
            accountHolderRepository.delete(accountHolder);
            return new ResponseEntity<>(accountHolder.getName() + " deleted successful", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Invalid hospital", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> changePassword(String old, String newPassword, Principal principal) {
        AccountHolder accountHolder=this.findByEmail(principal.getName());
        if(old.length()==0)return new ResponseEntity<>("Provide old password",HttpStatus.BAD_REQUEST);
        if(newPassword.length()==0)return new ResponseEntity<>("Provide new password",HttpStatus.BAD_REQUEST);
        accountHolder.setPassword(newPassword);
        accountHolder=accountHolderRepository.save(accountHolder);
       return new ResponseEntity<>("Hi, "+accountHolder.getName()+" Your account password has changed successful",HttpStatus.ACCEPTED);
    }

    public AccountHolder findByEmail(String email) {
       return accountHolderRepository.findByEmail(email);
    }
    public AccountHolderDTO findByEmailDto(String email) {
       AccountHolder accountHolder= this.findByEmail(email);
       if(accountHolder==null)return null;
       DateTimeFormatter formatter =DateTimeFormatter.ofPattern("dd-MMMM-yyy");
        return new AccountHolderDTO(accountHolder.getId(), accountHolder.getName(), accountHolder.getGender(), accountHolder.getEmail(), accountHolder.getPhoneNumber(), "data:image/png;base64,"+Base64.getEncoder().encodeToString(accountHolder.getPhoto()), formatter.format(accountHolder.getDob()), accountHolder.getRole());
     }
    public AccountHolderDTO findAccountHolderById(String accountHolderId) {
      try{
       AccountHolder accountHolder=accountHolderRepository.findById(UUID.fromString(accountHolderId)).orElseThrow();
       DateTimeFormatter formatter =DateTimeFormatter.ofPattern("dd-MMMM-yyy");
        return new AccountHolderDTO(accountHolder.getId(), accountHolder.getName(), accountHolder.getGender(), accountHolder.getEmail(), accountHolder.getPhoneNumber(), "data:image/png;base64,"+Base64.getEncoder().encodeToString(accountHolder.getPhoto()), formatter.format(accountHolder.getDob()), accountHolder.getRole());
    }catch(Exception e){
        return new AccountHolderDTO();
    } 
    }
    public AccountHolderDTO findAccountHolderById(UUID id) {
        try{AccountHolder accountHolder=accountHolderRepository.findById(id).orElseThrow();
        DateTimeFormatter formatter =DateTimeFormatter.ofPattern("dd-MMMM-yyy");
        return new AccountHolderDTO(accountHolder.getId(), accountHolder.getName(), accountHolder.getGender(), accountHolder.getEmail(), accountHolder.getPhoneNumber(), "data:image/png;base64,"+Base64.getEncoder().encodeToString(accountHolder.getPhoto()), formatter.format(accountHolder.getDob()), accountHolder.getRole());
    }catch(Exception e){
        return new  AccountHolderDTO();
    }
}
public AccountHolder findAccountHolder(UUID id) {
  return accountHolderRepository.findById(id).orElse(null);
     
}
}
