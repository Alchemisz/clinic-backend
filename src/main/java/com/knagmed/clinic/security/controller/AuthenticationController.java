package com.knagmed.clinic.security.controller;

import com.knagmed.clinic.security.auth.AppUserDetailsService;
import com.knagmed.clinic.security.jwt.JwtUtil;
import com.knagmed.clinic.security.jwt.request.AuthenticationRequest;
import com.knagmed.clinic.security.jwt.request.AuthenticationResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/authenticate")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final AppUserDetailsService appUserService;

    @PostMapping
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()
                    )
            );
        }catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final UserDetails userDetails = appUserService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}
