package com.knagmed.clinic.patient.client;

import com.knagmed.clinic.entity.Patient;
import com.knagmed.clinic.exception.ApiRequestException;
import com.knagmed.clinic.patient.PatientFacade;
import com.knagmed.clinic.patient.client.command.CreatePatientCommand;
import com.knagmed.clinic.patient.client.command.CreatePeselCommand;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patient")
@AllArgsConstructor
public class PatientController {

    private final PatientFacade patientFacade;

    @GetMapping("/pageable")
    public Page<Patient> getPatients(@RequestParam Optional<Integer> page, @RequestParam Optional<String> value, @RequestParam Optional<String> sortBy) {
        return patientFacade.getByPagination(page, value, sortBy);
    }

    @GetMapping
    public List<Patient> getPatients() {
        return patientFacade.getAllPatients();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        patientFacade.deletePatientAndRelatedVisitsByPatientPesel(id);
    }

    @PostMapping
    public void addPatient(@Valid @RequestBody CreatePatientCommand command) {
        patientFacade.addPatient(command);
    }

    @GetMapping("/{pesel}")
    public Patient getById(@PathVariable String pesel) {
        return patientFacade.findPatientByPesel(pesel);
    }

    @PutMapping
    public ResponseEntity<Patient> update(@RequestBody Patient t) {
        throw new ApiRequestException("This method is not implemented yet!");
    }

    @GetMapping("/pesel")
    public String getUserPesel() {
        return patientFacade.getCurrentLoggedUserPesel();
    }

    @PostMapping("/pesel/generate")
    public String generatePesel(@Valid @RequestBody CreatePeselCommand command) {
        return patientFacade.generatePesel(command);
    }
}
