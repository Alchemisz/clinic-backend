package com.knagmed.clinic.security.auth;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public enum AppUserRole {

    USER("ROLE_USER"), ADMIN("ROLE_ADMIN");

    private final String roleName;

    AppUserRole(String roleName) {
        this.roleName = roleName;
    }

    public List<SimpleGrantedAuthority> getGrantedAuthorities(){
        return List.of(new SimpleGrantedAuthority(roleName));
    }

}
