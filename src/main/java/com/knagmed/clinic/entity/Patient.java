package com.knagmed.clinic.entity;

import com.knagmed.clinic.security.auth.AppUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@Data
public class Patient extends Person{

    @Id
    @Column(name = "pesel", unique = true, nullable = false)
    private String pesel;

    public Patient(@NotNull String firstName, @NotNull String lastName, AppUser appUser, Address address, String pesel) {
        super(firstName, lastName, appUser, address);
        this.pesel = pesel;
    }
}
