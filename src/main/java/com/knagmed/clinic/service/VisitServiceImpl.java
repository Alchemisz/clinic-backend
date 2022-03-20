package com.knagmed.clinic.service;

import com.knagmed.clinic.customRequest.VisitRequest;
import com.knagmed.clinic.dao.VisitRepository;
import com.knagmed.clinic.entity.Doctor;
import com.knagmed.clinic.entity.Patient;
import com.knagmed.clinic.entity.Visit;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class VisitServiceImpl implements VisitService {

    private VisitRepository visitRepository;
    private PatientService patientService;
    private DoctorService doctorService;

    @Override
    public Visit save(Visit visit) {
        return visitRepository.save(visit);
    }

    @Override
    public Optional<Visit> getById(Long id) {
        return visitRepository.findById(id);
    }

    @Override
    public List<Visit> getAll() {
        return visitRepository.findAll();
    }

    @Override
    public Visit save(VisitRequest visitRequest) {
        Optional<Patient> patient = patientService.getById(visitRequest.getPatientId());
        Optional<Doctor> doctor = doctorService.getById(visitRequest.getDoctorId());

        if (patient.isPresent() && doctor.isPresent()){
            Visit visit = new Visit(visitRequest.getVisitDate());
            visit.setDoctor(doctor.get());
            visit.setPatient(patient.get());
            return visitRepository.save(visit);
        }

        return null;
    }

    @Override
    public Page<Visit> getByVisitDatePagination(LocalDate localDate, Optional<Integer> page) {
        return visitRepository.findByVisitDate(
                localDate,
                PageRequest.of(
                        page.orElse(0),
                        5
                ));
    }
}
