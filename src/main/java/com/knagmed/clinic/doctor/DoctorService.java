package com.knagmed.clinic.doctor;

import com.knagmed.clinic.common.AddressRepository;
import com.knagmed.clinic.doctor.command.CreateDoctorCommand;
import com.knagmed.clinic.doctor.command.UpdateDoctorCommand;
import com.knagmed.clinic.doctor.dao.DoctorRepository;
import com.knagmed.clinic.doctor.dao.SpecializationRepository;
import com.knagmed.clinic.doctor.dto.DoctorDTO;
import com.knagmed.clinic.entity.Address;
import com.knagmed.clinic.entity.Doctor;
import com.knagmed.clinic.entity.Specialization;
import com.knagmed.clinic.visit.dao.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final VisitRepository visitRepository;
    private final SpecializationRepository specializationRepository;
    private final DoctorRepository doctorRepository;
    private final AddressRepository addressRepository;

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

    public Doctor save(CreateDoctorCommand command) {
        Address address = new Address(command.getPostCode(), command.getCity(), command.getHomeNumber());

        List<Specialization> specializations = Arrays.stream(command.getSpecializations())
            .map(s -> {
                Optional<Specialization> spec = specializationRepository.findByName(s);
                return spec.orElseGet(() -> specializationRepository.save(new Specialization(s)));
            })
            .collect(Collectors.toList());

        Address saved = addressRepository.save(address);
        Doctor entity = new Doctor(
            command.getFirstName(),
            command.getLastName(),
            null,
            saved,
            specializations
        );
        return doctorRepository.save(entity);
    }

    public void updateDoctor(UpdateDoctorCommand command, Long id) {
        Address address = new Address(command.getPostCode(), command.getCity(), command.getHomeNumber());
        Doctor doctor = doctorRepository.findById(id).get();

        List<String> specializationsNames = specializationRepository.findAll().stream().map(Specialization::getName).collect(Collectors.toList());
        List<Specialization> newSpecs = new ArrayList<>();
        Arrays.asList(command.getChoosenSpecializations()).forEach(specPassed -> {
                if (specializationsNames.contains(specPassed)) {
                    Specialization specialization = specializationRepository.findByName(specPassed).get();
                    newSpecs.add(specialization);
                } else {
                    Specialization save = specializationRepository.save(new Specialization(specPassed));
                    newSpecs.add(save);
                }
            }
        );

        Address saved = addressRepository.save(address);
        doctor.setSpecializations(newSpecs);
        doctor.setFirstName(command.getFirstName());
        doctor.setLastName(command.getLastName());
        doctor.setAddress(saved);

        doctorRepository.save(doctor);
    }
}
