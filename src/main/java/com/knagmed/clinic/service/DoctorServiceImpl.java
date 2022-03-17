package com.knagmed.clinic.service;

import com.knagmed.clinic.dao.DoctorRepository;
import com.knagmed.clinic.entity.Doctor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;

    @Override
    public Doctor save(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Optional<Doctor> getById(Long id) {
        return doctorRepository.findById(id);
    }

    @Override
    public List<Doctor> getAll() {
        return doctorRepository.findAll();
    }
}
