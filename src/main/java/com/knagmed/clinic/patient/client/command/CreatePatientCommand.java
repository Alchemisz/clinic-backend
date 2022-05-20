package com.knagmed.clinic.patient.client.command;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePatientCommand {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String pesel;
    @NotNull
    private Address address;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Address {
        @NotNull
        private String postCode;
        @NotNull
        private String city;
        @NotNull
        private String houseNumber;
    }
}
