package com.ed_health_backend_v1.configuration.security;

// import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
// import com.ed_health_backend_v1.enums.Role;
import com.ed_health_backend_v1.modal.AccountHolder;
// import com.ed_health_backend_v1.repository.AccountHolderRepository;
import com.ed_health_backend_v1.services.AccountHolderServices;

@Service
public class UserDetailServices implements UserDetailsService {
    @Autowired
    private AccountHolderServices accountHolderServices;
    // @Autowired
    // private AccountHolderRepository accountHolderRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // AccountHolder accountHolder1 = new AccountHolder("admin", "Male", "admin@gmail.com", "07883256", null,
        //         LocalDate.now(), BCrypt.hashpw("admin", BCrypt.gensalt()), Role.ROLE_ADMIN);
        // AccountHolder result = accountHolderRepository.save(accountHolder1);
        AccountHolder accountHolder2 = accountHolderServices.findByEmail(username);
        if (accountHolder2 == null)
            throw new UsernameNotFoundException("Unimplemented method  loadUserByUsername");
        return new UserDetailPrinciple(accountHolder2);
    }
}