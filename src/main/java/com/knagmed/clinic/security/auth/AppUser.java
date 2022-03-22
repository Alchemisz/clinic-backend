package com.knagmed.clinic.security.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class AppUser {

    @Id
    @Column(unique = true)
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
