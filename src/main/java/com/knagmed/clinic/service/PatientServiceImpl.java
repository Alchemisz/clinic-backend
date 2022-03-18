package com.knagmed.clinic.service;

import com.knagmed.clinic.dao.PatientRepository;
import com.knagmed.clinic.entity.Patient;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Setter
public class PatientServiceImpl implements PatientService {

    @Value("${patient.page.size}")
    private Integer patientPageSize;

    @Value("${patient.default.sort.field}")
    private String patientDefaultSortField;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    private final PatientRepository patientRepository;

    @Override
    public Patient save(Patient patient) {

        try {
            if (patientRepository.findPatientByPesel(patient.getPesel()).isPresent())
                throw new SQLException("PATIENT WITH THAT PESEL ALREADY EXISTS!");
        } catch (SQLException exception) {
            exception.printStackTrace();
            return null;
        }

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

    @Override
    public Page<Patient> getAllByPagination(Optional<Integer> page, Optional<String> sortBy) {
        return patientRepository.findAll(
                PageRequest.of(
                        page.orElse(0),
                        patientPageSize,
                        Sort.Direction.ASC, sortBy.orElse(patientDefaultSortField)
                )
        );
    }
}
