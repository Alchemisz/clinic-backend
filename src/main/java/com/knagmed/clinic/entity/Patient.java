package com.knagmed.clinic.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Patient {

    @Id
    @Column(name = "pesel", nullable = false, length = 11)
    private Long pesel;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    public Patient(Long pesel, String firstName, String lastName, Address address) {
        this.pesel = pesel;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }
}
