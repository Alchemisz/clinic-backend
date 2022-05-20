package com.knagmed.clinic.patient;

import com.knagmed.clinic.entity.Patient;
import com.knagmed.clinic.patient.client.command.CreatePatientCommand;
import com.knagmed.clinic.patient.client.command.CreatePeselCommand;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PatientFacade {

    private final PatientService patientService;
    private final PeselGenerator peselGenerator;

    public Page<Patient> getByPagination(Optional<Integer> page, Optional<String> value, Optional<String> sortBy) {
        return patientService.getByPagination(page, value, sortBy);
    }

    public List<Patient> getAllPatients() {
        return patientService.getAll();
    }

    public void deletePatientAndRelatedVisitsByPatientPesel(String id) {
        patientService.deletePatientAndRelatedVisitdByPesel(id);
    }

    public void addPatient(CreatePatientCommand command) {
        patientService.save(command);
    }

    public Patient findPatientByPesel(String pesel) {
        return patientService.getPatientByPesel(pesel);
    }

    public String getCurrentLoggedUserPesel() {
        return patientService.getUserPesel();
    }

    public String generatePesel(CreatePeselCommand command) {
        return peselGenerator.generate(command.getDateOfBirth());
    }
}
