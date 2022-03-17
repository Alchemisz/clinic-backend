package com.knagmed.clinic.service;

import com.knagmed.clinic.entity.Doctor;
import com.knagmed.clinic.entity.Patient;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

public interface DoctorService {

    Doctor save(Doctor doctor);
    Optional<Doctor> getById(Long id);
    List<Doctor> getAll();

}
