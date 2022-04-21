package com.knagmed.clinic.service.doctor;

import com.knagmed.clinic.dao.DoctorRepository;
import com.knagmed.clinic.dao.SpecializationRepository;
import com.knagmed.clinic.dao.VisitRepository;
import com.knagmed.clinic.dto.DoctorDTO;
import com.knagmed.clinic.entity.Doctor;
import com.knagmed.clinic.entity.Specialization;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl extends DoctorService {

    private final VisitRepository visitRepository;
    private final SpecializationRepository specializationRepository;

    public DoctorServiceImpl(DoctorRepository repository, VisitRepository visitRepository, SpecializationRepository specializationRepository) {
        super(repository);
        this.visitRepository = visitRepository;
        this.specializationRepository = specializationRepository;
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

    @Transactional
    @Override
    public void deleteDoctorAndVisitsByDoctorId(Long id) {
        visitRepository.deleteAllByDoctorId(id);
        repository.deleteById(id);
    }

    @Override
    public List<Specialization> getAllSpecializations() {
        return specializationRepository.findAll();
    }

    @Override
    public List<DoctorDTO> getAllDoctors() {
        return repository.findAll().stream()
                .map(doctor -> DoctorDTO.builder()
                        .id(doctor.getId())
                        .firstName(doctor.getFirstName())
                        .lastName(doctor.getLastName())
                        .build())
                .collect(Collectors.toList());
    }

}
