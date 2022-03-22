package com.knagmed.clinic.security.config;

import com.knagmed.clinic.security.auth.AppUser;
import com.knagmed.clinic.security.auth.AppUserRepository;
import com.knagmed.clinic.security.auth.AppUserRole;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserInit {

    private final PasswordEncoder passwordEncoder;
    private final AppUserRepository appUserRepository;

    public UserInit(PasswordEncoder passwordEncoder, AppUserRepository appUserRepository) {
        this.passwordEncoder = passwordEncoder;
        this.appUserRepository = appUserRepository;

        initUsers();
    }

    private void initUsers() {
        appUserRepository.save(new AppUser("user", passwordEncoder.encode("password"), List.of(AppUserRole.USER)));
        appUserRepository.save(new AppUser("admin", passwordEncoder.encode("password"), List.of(AppUserRole.ADMIN)));
    }
}
