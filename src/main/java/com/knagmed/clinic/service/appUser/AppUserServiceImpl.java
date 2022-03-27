package com.knagmed.clinic.service.appUser;

import com.knagmed.clinic.security.auth.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AppUserServiceImpl implements AppUserService {

    private AppUserRepository appUserRepository;

    @Override
    public Boolean isUserExists(String username) {
        return appUserRepository.existsByUsername(username);
    }
}
