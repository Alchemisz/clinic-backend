package com.knagmed.clinic.services;

import com.knagmed.clinic.entity.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientService {

    Patient save(Patient patient);
    Optional<Patient> getById(Long id);
    List<Patient> getAll();
}
