package com.knagmed.clinic.service.visit;

import com.knagmed.clinic.customRequest.VisitRequest;
import com.knagmed.clinic.dao.VisitRepository;
import com.knagmed.clinic.dto.VisitDTO;
import com.knagmed.clinic.entity.Doctor;
import com.knagmed.clinic.entity.Patient;
import com.knagmed.clinic.entity.Visit;
import com.knagmed.clinic.service.doctor.DoctorService;
import com.knagmed.clinic.service.patient.PatientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VisitServiceImpl extends VisitService {

    private final PatientService patientService;
    private final DoctorService doctorService;

    @Value("${visit.page.size}")
    private Integer visitPageSize;

    public VisitServiceImpl(VisitRepository repository, PatientService patientService, DoctorService doctorService) {
        super(repository);
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    @Override
    public Visit save(VisitRequest visitRequest) {
        Optional<Patient> patient = patientService.getById(visitRequest.getPatientId());
        Optional<Doctor> doctor = doctorService.getById(visitRequest.getDoctorId());

        if (patient.isPresent() && doctor.isPresent()) {
            Visit visit = new Visit(visitRequest.getVisitDate());
            visit.setDoctor(doctor.get());
            visit.setPatient(patient.get());
            return repository.save(visit);
        }

        return null;
    }

    @Override
    public Page<Visit> getByVisitDatePagination(LocalDate localDate, Optional<Integer> page) {
        return repository.findByVisitDate(
                localDate,
                PageRequest.of(
                        page.orElse(0),
                        visitPageSize
                ));
    }

    @Override
    public Page<VisitDTO> getUpcomingVisits(Optional<Integer> page) {
        Page<Visit> visitPage = repository.findByVisitDateIsGreaterThanEqual(
                LocalDate.now(),
                PageRequest.of(
                        page.orElse(0),
                        visitPageSize
                )
        );
        PageImpl<VisitDTO> visitDTOS = mapToVisitDTOsPage(visitPage, visitPage.getPageable(), visitPage.getTotalElements());
        return visitDTOS;
    }

    @Override
    public Page<VisitDTO> getFinishedVisits(Optional<Integer> page) {
        Page<Visit> visitPage = repository.findByVisitDateLessThan(
                LocalDate.now(),
                PageRequest.of(
                        page.orElse(0),
                        visitPageSize
                )
        );
        PageImpl<VisitDTO> visitDTOS = mapToVisitDTOsPage(visitPage, visitPage.getPageable(), visitPage.getTotalElements());
        return visitDTOS;
    }

    @Override
    public void deleteVisitById(Long id) {
        repository.deleteById(id);
    }

    private PageImpl<VisitDTO> mapToVisitDTOsPage(Page<Visit> visitPage, Pageable pageable, long totalElements) {
        List<VisitDTO> visitDTOList = visitPage
                .get()
                .map(visit -> VisitDTO.builder()
                        .visitId(visit.getId())
                        .visitDate(visit.getVisitDate())
                        .patientData(visit.getPatient().getFirstName() + " " + visit.getPatient().getLastName())
                        .doctorData(visit.getDoctor().getFirstName() + " " + visit.getDoctor().getLastName())
                        .build())
                .collect(Collectors.toList());
        PageImpl<VisitDTO> visitDTOS = new PageImpl<>(visitDTOList, pageable, totalElements);
        return visitDTOS;
    }
}
