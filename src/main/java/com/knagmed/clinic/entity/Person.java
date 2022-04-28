package com.knagmed.clinic.entity;

import com.knagmed.clinic.security.auth.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class Person {

    @NotNull
    protected String firstName;

    @NotNull
    protected String lastName;

    @OneToOne
    @JoinColumn(name = "userId")
    protected AppUser appUser;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

}
