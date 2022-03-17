package com.knagmed.clinic.controller;

import com.knagmed.clinic.entity.Address;
import com.knagmed.clinic.entity.Patient;
import com.knagmed.clinic.services.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patient")
@AllArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping()
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient){
        Patient save = patientService.save(patient);
        return new ResponseEntity<Patient>(save, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatient(@PathVariable Long id){
        Optional<Patient> patientOptional = patientService.getById(id);
        return patientOptional.map(patient -> new ResponseEntity<>(patient, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @GetMapping("")
    public ResponseEntity<List<Patient>> getPatient(){
        return new ResponseEntity<>(patientService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<Patient> getTest(){
        return new ResponseEntity<>(
                new Patient(99330011224L,"Karol", "Ziemba", new Address("25-007", "Kielce", "2Z"))
                ,HttpStatus.OK
        );
    }
}
