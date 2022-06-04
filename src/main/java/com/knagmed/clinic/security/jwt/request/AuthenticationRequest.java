package com.knagmed.clinic.security.jwt.request;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Data
public class AuthenticationRequest {
    @NotEmpty(message = "Pole nazwa użytkownika nie może być puste!")
    String username;
    @NotEmpty(message = "Pole hasło nie może być puste!")
    String password;
}
