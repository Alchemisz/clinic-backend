package com.knagmed.clinic.service.patient;

import com.knagmed.clinic.dao.PatientRepository;
import com.knagmed.clinic.entity.Patient;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Setter
public class PatientServiceImpl extends PatientService {

    @Value("${patient.page.size}")
    private Integer patientPageSize;

    @Value("${patient.default.sort.field}")
    private String patientDefaultSortField;

    public PatientServiceImpl(PatientRepository repository) {
        super(repository);
    }

    @Override
    public Page<Patient> getByPagination(Optional<Integer> page, Optional<String> value, Optional<String> sortBy) {

        PageRequest paginationSetup = PageRequest.of(
                page.orElse(0),
                patientPageSize,
                Sort.Direction.ASC, sortBy.orElse(patientDefaultSortField)
        );
        if (value.isPresent()){
            String searchPhrase = value.get();
            if (searchPhrase.length() == 11)
                return repository.findPatientsByPeselEquals(Long.valueOf(searchPhrase), paginationSetup);
            return  repository.findPatientsByPeselGreaterThanEqual(Long.valueOf(searchPhrase + "0".repeat(11 - searchPhrase.length())), paginationSetup);
        }



        return repository.findAll(paginationSetup);
    }

}
