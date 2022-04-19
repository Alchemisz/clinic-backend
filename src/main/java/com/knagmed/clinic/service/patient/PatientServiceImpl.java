package com.knagmed.clinic.service.patient;

import com.knagmed.clinic.dao.PatientRepository;
import com.knagmed.clinic.dao.VisitRepository;
import com.knagmed.clinic.entity.Patient;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Setter
public class PatientServiceImpl extends PatientService {

    private final VisitRepository visitRepository;

    @Value("${patient.page.size}")
    private Integer patientPageSize;

    @Value("${patient.default.sort.field}")
    private String patientDefaultSortField;

    public PatientServiceImpl(PatientRepository repository, VisitRepository visitRepository) {
        super(repository);
        this.visitRepository = visitRepository;
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

    @Transactional
    @Override
    public void deletePatientAndVisitsByPatientPesel(Long patientPesel) {
        visitRepository.deleteAllByPatientPesel(patientPesel);
        repository.deleteById(patientPesel);
    }


}
