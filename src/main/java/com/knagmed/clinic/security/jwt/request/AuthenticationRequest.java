package com.knagmed.clinic.security.jwt.request;

import lombok.*;

@Data
public class AuthenticationRequest {
    private String username;
    private String password;
}
