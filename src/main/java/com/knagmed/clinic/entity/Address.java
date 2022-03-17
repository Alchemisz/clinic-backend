package com.knagmed.clinic.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String postCode;
    private String city;
    private String houseNumber;

    public Address(String postCode, String city, String houseNumber) {
        this.postCode = postCode;
        this.city = city;
        this.houseNumber = houseNumber;
    }
}
