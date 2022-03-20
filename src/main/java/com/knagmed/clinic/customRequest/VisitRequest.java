package com.knagmed.clinic.customRequest;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class VisitRequest {

    private LocalDate visitDate;
    private Long patientId;
    private Long doctorId;

}
