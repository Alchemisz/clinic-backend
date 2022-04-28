package com.knagmed.clinic.doctor.command;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Data
public class DoctorCreateCommand {
  @NotNull(message = "firstName can not be null")
  private String firstName;
  @NotNull(message = "lastName can not be null")
  private String lastName;
  private String city;
  private String postCode;
  private String homeNumber;
  @NotNull(message = "specializations can not be null")
  private String[] specializations;
}
