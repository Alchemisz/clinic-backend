package com.knagmed.clinic.patient.client.command;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdatePatientCommand {
    @NotNull private String pesel;
    @NotNull private String firstName;
    @NotNull private String lastName;
    @NotNull private String city;
    @NotNull private String homeNumber;
    @NotNull private String postCode;
}
