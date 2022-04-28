package com.knagmed.clinic.patient;

import com.knagmed.clinic.patient.dao.PatientRepository;
import com.knagmed.clinic.visit.dao.VisitRepository;
import com.knagmed.clinic.entity.Patient;
import com.knagmed.clinic.security.auth.AppUser;
import com.knagmed.clinic.appuser.LoggedUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final VisitRepository visitRepository;
    private final LoggedUserService loggedUserService;
    private final PatientRepository patientRepository;

    @Value("${patient.page.size}")
    private Integer patientPageSize;

    @Value("${patient.default.sort.field}")
    private String patientDefaultSortField;

    public Page<Patient> getByPagination(Optional<Integer> page, Optional<String> value, Optional<String> sortBy) {

        PageRequest paginationSetup = PageRequest.of(
                page.orElse(0),
                patientPageSize,
                Sort.Direction.ASC, sortBy.orElse(patientDefaultSortField)
        );
        if (value.isPresent()){
            String searchPhrase = value.get();
            if (searchPhrase.length() == 11)
                return patientRepository.findPatientsByPeselEquals(Long.valueOf(searchPhrase), paginationSetup);
            return  patientRepository.findPatientsByPeselGreaterThanEqual(Long.valueOf(searchPhrase + "0".repeat(11 - searchPhrase.length())), paginationSetup);
        }

        return patientRepository.findAll(paginationSetup);
    }

    @Transactional
    public void deletePatientAndRelatedVisitdByPesel(Long patientPesel) {
        visitRepository.deleteAllByPatientPesel(patientPesel);
        patientRepository.deleteById(patientPesel);
    }

    public Long getUserPesel() {
        AppUser appUser = loggedUserService.getCurrentLoggedUser();
        Patient patientByAppUser = patientRepository.findPatientByAppUser(appUser);
        return patientByAppUser.getPesel();
    }

    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    public Patient getPatientByPesel(Long pesel) {
        return patientRepository.findById(pesel).
                orElseThrow(() -> new IllegalArgumentException(String.format("Can not find patient with ID = %d", pesel)));
    }

    @Transactional
    public void save(Patient patient) {
        patientRepository.save(patient);
    }
}
