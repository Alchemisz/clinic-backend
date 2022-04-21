package com.knagmed.clinic.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class DoctorDTO {
    Long id;
    String firstName;
    String lastName;
}
