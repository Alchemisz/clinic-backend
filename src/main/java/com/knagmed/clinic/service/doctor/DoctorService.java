package com.knagmed.clinic.service.doctor;

import com.knagmed.clinic.dao.DoctorRepository;
import com.knagmed.clinic.entity.Doctor;
import com.knagmed.clinic.service.BasicCrudServiceImpl;
import org.springframework.data.domain.Page;

import java.util.Optional;

public abstract class DoctorService extends BasicCrudServiceImpl<Doctor, Long, DoctorRepository> {

    public DoctorService(DoctorRepository repository) {
        super(repository);
    }

    public abstract Page<Doctor> getByPagination(Optional<Integer> page, Optional<String> sortBy);
}
