package com.knagmed.clinic.entity;

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
    private Long pesel;

    public Patient(Long pesel,@NotNull String firstName,@NotNull String lastName, Address address) {
        super(firstName, lastName, address);
        this.pesel = pesel;
    }
}
