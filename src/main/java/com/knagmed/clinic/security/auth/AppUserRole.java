package com.knagmed.clinic.security.auth;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@Getter
public enum AppUserRole {

    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private final String roleName;

    AppUserRole(String roleName) {
        this.roleName = roleName;
    }

    public List<SimpleGrantedAuthority> getGrantedAuthorities(){
        return List.of(new SimpleGrantedAuthority(roleName));
    }

}
