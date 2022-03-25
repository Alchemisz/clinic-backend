package com.knagmed.clinic.service.patient;

import com.knagmed.clinic.dao.PatientRepository;
import com.knagmed.clinic.entity.Patient;
import com.knagmed.clinic.service.BasicCrudServiceImpl;
import org.springframework.data.domain.Page;

import java.util.Optional;

public abstract class PatientService extends BasicCrudServiceImpl<Patient, Long, PatientRepository> {

    public PatientService(PatientRepository repository) {
        super(repository);
    }

    public abstract Page<Patient> getByPagination(Optional<Integer> page, Optional<String> value, Optional<String> sortBy);
}
