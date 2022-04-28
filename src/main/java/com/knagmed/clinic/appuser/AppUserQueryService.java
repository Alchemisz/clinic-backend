package com.knagmed.clinic.appuser;

import com.knagmed.clinic.security.auth.AppUserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AppUserQueryService {

    private AppUserRepository appUserRepository;

    public Boolean isUserExists(String username) {
        return appUserRepository.existsByUsername(username);
    }
}
