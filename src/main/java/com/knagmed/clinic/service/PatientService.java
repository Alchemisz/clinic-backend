package com.knagmed.clinic.service;

import com.knagmed.clinic.entity.Patient;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface PatientService extends BasicCrudService<Patient, Long> {

    Page<Patient> getAllByPagination(Optional<Integer> page, Optional<String> sortBy);
}
