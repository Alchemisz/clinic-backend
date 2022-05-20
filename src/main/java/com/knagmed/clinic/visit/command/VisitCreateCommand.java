package com.knagmed.clinic.visit.command;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class VisitCreateCommand {
    private Long doctorId;
    private String patientPesel;
    private LocalDate visitDate;
}
