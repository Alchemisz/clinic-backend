package com.knagmed.clinic.doctor;

import com.knagmed.clinic.doctor.dao.DoctorRepository;
import com.knagmed.clinic.doctor.dao.SpecializationRepository;
import com.knagmed.clinic.visit.dao.VisitRepository;
import com.knagmed.clinic.doctor.dto.DoctorDTO;
import com.knagmed.clinic.entity.Doctor;
import com.knagmed.clinic.entity.Specialization;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class DoctorService{

    private final VisitRepository visitRepository;
    private final SpecializationRepository specializationRepository;
    private final DoctorRepository doctorRepository;

    @Value("${doctor.page.size}")
    private Integer doctorPageSize;

    @Value("${doctor.default.sort.field}")
    private String doctorsDefaultSortField;

    public Page<Doctor> getByPagination(Optional<Integer> page, Optional<String> sortBy) {
        PageRequest paginationSetup = PageRequest.of(
                page.orElse(0),
                doctorPageSize,
                Sort.Direction.ASC, sortBy.orElse(doctorsDefaultSortField)
        );

        return doctorRepository.findAll(paginationSetup);
    }

    @Transactional
    public void deleteDoctorAndVisitsByDoctorId(Long id) {
        visitRepository.deleteAllByDoctorId(id);
        doctorRepository.deleteById(id);
    }

    public List<Specialization> getAllSpecializations() {
        return specializationRepository.findAll();
    }

    public List<DoctorDTO> getAllDoctors() {
        return doctorRepository.findAll().stream()
                .map(doctor -> DoctorDTO.builder()
                        .id(doctor.getId())
                        .firstName(doctor.getFirstName())
                        .lastName(doctor.getLastName())
                        .build())
                .collect(Collectors.toList());
    }

    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException(String.format("Patient with ID=%d not found", id)));
    }

    public Doctor save(Doctor t) {
        return doctorRepository.save(t);
    }

    public List<Doctor> getAll() {
        return doctorRepository.findAll();
    }
}
