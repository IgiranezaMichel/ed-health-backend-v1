package com.ed_health_backend_v1.mapper;

import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.function.Function;

import com.ed_health_backend_v1.dto.AccountHolderDTO;
import com.ed_health_backend_v1.modal.AccountHolder;

public class AccountHolderMapper implements Function<AccountHolder, AccountHolderDTO> {

  @Override
  public AccountHolderDTO apply(AccountHolder t) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyy");
    String base64Img = "";
    if (t.getPhoto() != null)
      base64Img = "data:image/png;base64," + Base64.getEncoder().encodeToString(t.getPhoto());
    return new AccountHolderDTO(t.getId(), t.getName(), t.getGender(), t.getEmail(), t.getPhoneNumber(), base64Img,
        formatter.format(t.getDob()), t.getRole());
  }

}
