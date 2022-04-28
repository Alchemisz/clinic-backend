package com.knagmed.clinic.doctor.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class DoctorDTO {
    Long id;
    String firstName;
    String lastName;
}
