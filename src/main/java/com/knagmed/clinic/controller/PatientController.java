package com.knagmed.clinic.controller;

import com.knagmed.clinic.entity.Patient;
import com.knagmed.clinic.service.patient.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patient")
@AllArgsConstructor
public class PatientController{

    private final PatientService patientService;

    @PostMapping()
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient){
        Patient save = patientService.save(patient);

        HttpStatus responseCode = save == null ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED;

        return new ResponseEntity<>(save, responseCode);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatient(@PathVariable Long id){
        Optional<Patient> patientOptional = patientService.getById(id);
        return patientOptional.map(patient -> new ResponseEntity<>(patient, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @GetMapping("/pageable")
    public Page<Patient> getPatients(@RequestParam Optional<Integer> page, @RequestParam Optional<String> sortBy){
        return patientService.getAllByPagination(page, sortBy);
    }

    @GetMapping()
    public ResponseEntity<List<Patient>> getPatients(){
        List<Patient> patients = patientService.getAll();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }
}
