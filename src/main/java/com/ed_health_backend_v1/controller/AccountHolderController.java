package com.ed_health_backend_v1.controller;

import java.security.Principal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ed_health_backend_v1.dto.AccountHolderDTO;
import com.ed_health_backend_v1.dto.Pagination;
import com.ed_health_backend_v1.services.AccountHolderServices;

@RestController
@RequestMapping(value = "/api/accountHolder")
public class AccountHolderController {
@Autowired private AccountHolderServices accountHolderServices;
@PostMapping(value = "create")
public ResponseEntity<String>createAccountHolder(@RequestBody AccountHolderDTO accountHolderDTO,@RequestParam(required=false) String password){
    return accountHolderServices.create(accountHolderDTO,password);
}

@PostMapping(value = "changePassword/{old}/{new}")
public ResponseEntity<String>changePassord(@PathVariable String old,@PathVariable(name = "new") String newPassword,Principal principal){
    return accountHolderServices.changePassword(old,newPassword,principal);
}
@GetMapping(value = "all")
public Pagination<AccountHolderDTO>AccountHolderList(@RequestParam() String search,@RequestParam(defaultValue = "name") String sort,@RequestParam(defaultValue = "0") int pageNumber,@RequestParam() int pageSize){
    return accountHolderServices.getAllAccountHolder(search,sort,pageNumber,pageSize);
}
@GetMapping(value = "/{id}")
public AccountHolderDTO findAccountHolderById(@PathVariable(name = "id") String accountHolderId){
    return accountHolderServices.findAccountHolderById(accountHolderId);
}
@GetMapping(value = "find")
public AccountHolderDTO findAccountHolderByEmail(@RequestParam(name = "email") String email){
    return accountHolderServices.findByEmailDto(email);
}
@DeleteMapping(value = "/{id}")
public  ResponseEntity<String>deleteAccountHolder(@PathVariable()String id){
    return accountHolderServices.delete(UUID.fromString(id));
}
}
