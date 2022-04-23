package com.knagmed.clinic.service.patient;

import com.knagmed.clinic.entity.Patient;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PatientFacade {

    private final PatientService patientService;

    public Page<Patient> getByPagination(Optional<Integer> page, Optional<String> value, Optional<String> sortBy) {
        return patientService.getByPagination(page, value, sortBy);
    }

    public List<Patient> getAllPatients() {
        return patientService.getAll();
    }

    public void deletePatientAndRelatedVisitsByPatientPesel(Long id) {
        patientService.deletePatientAndRelatedVisitdByPesel(id);
    }

    public void addPatient(Patient patient) {
        patientService.save(patient);
    }

    public Patient findPatientByPesel(Long pesel) {
        return patientService.getPatientByPesel(pesel);
    }

    public Long getCurrentLoggedUserPesel() {
        return patientService.getUserPesel();
    }
}
