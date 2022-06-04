package com.knagmed.clinic.doctor.command;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
public class UpdateDoctorCommand {
    @NotNull(message = "firstName can not be null")
    private String firstName;
    @NotNull(message = "lastName can not be null")
    private String lastName;
    @NotNull private String city;
    @NotNull private String postCode;
    @NotNull private String homeNumber;
    @NotNull(message = "specializations can not be null")
    private String[] choosenSpecializations;
}
