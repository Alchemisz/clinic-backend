package com.knagmed.clinic.appuser;

import com.knagmed.clinic.security.auth.AppUser;
import com.knagmed.clinic.security.auth.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class LoggedUserService {

    private final AppUserRepository appUserRepository;

    public AppUser getCurrentLoggedUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return appUserRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new IllegalStateException("Can not find current logged user!"));
    }

    public UserDetails getCurrentLoggedUserDetails(){
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public Collection<? extends GrantedAuthority> getCurrentLoggedUserRoles(){
        return ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getAuthorities();
    }

}
