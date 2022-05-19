package com.knagmed.clinic.patient.client.command;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class CreatePeselCommand {
  @NotNull private LocalDate dateOfBirth;
}
