


package com.knagmed.clinic.controller;

import com.knagmed.clinic.customRequest.VisitRequest;
import com.knagmed.clinic.entity.Address;
import com.knagmed.clinic.entity.Doctor;
import com.knagmed.clinic.entity.Patient;
import com.knagmed.clinic.entity.Visit;
import com.knagmed.clinic.service.VisitService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/visit")
@AllArgsConstructor
public class VisitController implements Testable<Visit>{

    private final VisitService visitService;

    @PostMapping
    public ResponseEntity<Visit> addVisit(@RequestBody VisitRequest visitRequest){
        Visit save = visitService.save(visitRequest);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Visit> getTestObject() {
        Visit visit = new Visit(new Date(2022, 3, 18));
        visit.setPatient(new Patient(99112288995L, "Adrian", "Zoo", new Address("26-003", "Kraków", "67A")));
        visit.setDoctor(new Doctor("Bartek", "Kruk",  new Address("21-023", "Warszawa", "3B")));
        return new ResponseEntity<>(visit, HttpStatus.OK);
    }
}
