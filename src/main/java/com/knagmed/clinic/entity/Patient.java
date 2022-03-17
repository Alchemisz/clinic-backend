package com.knagmed.clinic.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@Data
public class Patient extends Person{

    @Id
    @Column(name = "pesel", nullable = false)
    private Long pesel;

    public Patient(Long pesel,@NotNull String firstName,@NotNull String lastName, Address address) {
        super(firstName, lastName, address);
        this.pesel = pesel;
    }
}
