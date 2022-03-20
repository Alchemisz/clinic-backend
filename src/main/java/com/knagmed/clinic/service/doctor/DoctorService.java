package com.knagmed.clinic.service.doctor;

import com.knagmed.clinic.dao.DoctorRepository;
import com.knagmed.clinic.entity.Doctor;
import com.knagmed.clinic.entity.Patient;
import com.knagmed.clinic.service.BasicCrudService;
import com.knagmed.clinic.service.BasicCrudServiceImpl;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

public abstract class DoctorService extends BasicCrudServiceImpl<Doctor, Long, DoctorRepository> {

    public DoctorService(DoctorRepository repository) {
        super(repository);
    }
}
