package com.knagmed.clinic.visit.command;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class VisitCreateCommand {
    private Long doctorId;
    private Long patientPesel;
    private LocalDate visitDate;
}
