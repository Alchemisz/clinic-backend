package com.knagmed.clinic.controller;

import com.knagmed.clinic.entity.Patient;
import com.knagmed.clinic.service.patient.PatientService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patient")
public class PatientController extends CrudController<Patient, Long, PatientService> {

    public PatientController(PatientService service) {
        super(service);
    }

    @GetMapping("/pageable")
    public Page<Patient> getPatients(@RequestParam Optional<Integer> page, @RequestParam Optional<String> sortBy){
        return service.getAllByPagination(page, sortBy);
    }

    @GetMapping
    public ResponseEntity<List<Patient>> getPatients(){
        List<Patient> patients = service.getAll();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }
}
