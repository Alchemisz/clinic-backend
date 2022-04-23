package com.knagmed.clinic.service.appUser;

import com.knagmed.clinic.security.auth.AppUser;
import com.knagmed.clinic.security.auth.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoggedUserService {

    private final AppUserRepository appUserRepository;

    public AppUser getCurrentLoggedUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return appUserRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new IllegalStateException("Can not find current logged user!"));
    }


}
