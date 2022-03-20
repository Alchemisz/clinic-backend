package com.knagmed.clinic.service.doctor;

import com.knagmed.clinic.dao.DoctorRepository;
import com.knagmed.clinic.entity.Doctor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl extends DoctorService {

    public DoctorServiceImpl(DoctorRepository repository) {
        super(repository);
    }

}
