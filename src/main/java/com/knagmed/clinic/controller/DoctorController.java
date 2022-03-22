package com.knagmed.clinic.controller;

import com.knagmed.clinic.entity.Doctor;
import com.knagmed.clinic.service.doctor.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/doctor")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class DoctorController{

    private final DoctorService doctorService;

    @GetMapping("")
    public ResponseEntity<List<Doctor>> getDoctor(){
        return new ResponseEntity<>(doctorService.getAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor){
        Doctor save = doctorService.save(doctor);
        return new ResponseEntity<Doctor>(save, HttpStatus.CREATED);
    }

}
