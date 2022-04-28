package com.knagmed.clinic.security.auth;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String roles;

    public AppUser(String username, String password, List<AppUserRole> roles) {
        this.username = username;
        this.password = password;

        StringBuilder sb = new StringBuilder();
        roles.forEach(e -> sb.append("ROLE_" + e + ","));

        this.roles = sb.toString();
    }
}
