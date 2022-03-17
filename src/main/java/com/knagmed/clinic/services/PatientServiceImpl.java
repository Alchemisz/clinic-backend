package com.knagmed.clinic.services;

import com.knagmed.clinic.dao.PatientRepository;
import com.knagmed.clinic.entity.Patient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Optional<Patient> getById(Long id) {
        return patientRepository.findById(id);
    }

    @Override
    public List<Patient> getAll() {
        return patientRepository.findAll();
    }
}
