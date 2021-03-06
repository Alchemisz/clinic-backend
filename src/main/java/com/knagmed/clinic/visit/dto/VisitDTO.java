package com.knagmed.clinic.visit.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Builder
@Value
public class VisitDTO {
    Long visitId;
    LocalDate visitDate;
    String patientData;
    String doctorData;
}
