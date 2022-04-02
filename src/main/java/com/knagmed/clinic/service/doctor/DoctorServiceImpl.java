package com.knagmed.clinic.service.doctor;

import com.knagmed.clinic.dao.DoctorRepository;
import com.knagmed.clinic.entity.Doctor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DoctorServiceImpl extends DoctorService {

    public DoctorServiceImpl(DoctorRepository repository) {
        super(repository);
    }

    @Value("${doctor.page.size}")
    private Integer doctorPageSize;

    @Value("${doctor.default.sort.field}")
    private String doctorsDefaultSortField;

    @Override
    public Page<Doctor> getByPagination(Optional<Integer> page, Optional<String> sortBy) {
        PageRequest paginationSetup = PageRequest.of(
                page.orElse(0),
                doctorPageSize,
                Sort.Direction.ASC, sortBy.orElse(doctorsDefaultSortField)
        );

        return repository.findAll(paginationSetup);
    }

}
