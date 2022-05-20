package com.knagmed.clinic.security.controller;

import com.knagmed.clinic.security.auth.AppUser;
import com.knagmed.clinic.security.auth.AppUserDetails;
import com.knagmed.clinic.security.auth.AppUserRepository;
import com.knagmed.clinic.security.jwt.JwtUtil;
import com.knagmed.clinic.security.jwt.request.AuthenticationRequest;
import com.knagmed.clinic.security.jwt.request.AuthenticationResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/authenticate")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final AppUserRepository appUserRepository;

    @PostMapping
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    authenticationRequest.getUsername(),
                    authenticationRequest.getPassword()
                )
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final AppUser appUser = appUserRepository.findByUsername(authenticationRequest.getUsername()).get();
        final String jwt = jwtUtil.generateToken(new AppUserDetails(appUser));
        return ResponseEntity.ok(new AuthenticationResponse(appUser.getId(), jwt));
    }

}
