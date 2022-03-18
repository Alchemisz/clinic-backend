package com.knagmed.clinic.customRequest;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class VisitRequest {

    private Date visitDate;
    private Long patientId;
    private Long doctorId;

}
