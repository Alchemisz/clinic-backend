package com.knagmed.clinic.security.jwt.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class AuthenticationResponse implements Serializable {

    private final String jwt;


}
