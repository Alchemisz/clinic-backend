package com.knagmed.clinic.security.jwt.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class AuthenticationResponse implements Serializable {
    private final Long userId;
    private final String jwt;
}
