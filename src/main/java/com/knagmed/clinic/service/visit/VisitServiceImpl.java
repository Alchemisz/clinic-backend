package com.knagmed.clinic.service.visit;

import com.knagmed.clinic.dao.VisitRepository;
import com.knagmed.clinic.client.command.VisitCreateCommand;
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
import org.springframework.transaction.annotation.Transactional;

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
        Page<Visit> visitPage = repository.findByVisitDateIsGreaterThanEqualAndEndedEquals(
                LocalDate.now(),
                false,
                PageRequest.of(
                        page.orElse(0),
                        visitPageSize
                )
        );
        return mapToVisitDTOsPage(visitPage, visitPage.getPageable(), visitPage.getTotalElements());
    }

    @Override
    public Page<VisitDTO> getFinishedVisits(Optional<Integer> page) {
        Page<Visit> visitPage = repository.findByVisitDateLessThanOrEnded(
                LocalDate.now(),
                true,
                PageRequest.of(
                        page.orElse(0),
                        visitPageSize
                )
        );
        return mapToVisitDTOsPage(visitPage, visitPage.getPageable(), visitPage.getTotalElements());
    }

    @Override
    public void deleteVisitById(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    @Override
    public void addVisit(VisitCreateCommand command) {
        Patient patient = patientService.getPatientByPesel(command.getPatientPesel());
        Doctor doctor = doctorService.getDoctorById(command.getDoctorId());
        Visit visit = new Visit(command.getVisitDate());
        visit.setPatient(patient);
        visit.setDoctor(doctor);
        repository.save(visit);
    }

    @Override
    public List<VisitDTO> getVisitsByPatientPesel(Long pesel) {
        List<Visit> visitByPatient = repository.findVisitByEndedIsFalseAndPatientEquals(pesel);
        return visitByPatient.stream()
                .map(visit -> VisitDTO.builder()
                        .visitId(visit.getId())
                        .visitDate(visit.getVisitDate())
                        .patientData(visit.getPatient().getFirstName() + " " + visit.getPatient().getLastName())
                        .doctorData(visit.getDoctor().getFirstName() + " " + visit.getDoctor().getLastName())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void setEnded(Long id) {
        repository.setEndedById(id);
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
