package com.knagmed.clinic.visit;

import com.knagmed.clinic.visit.dao.VisitRepository;
import com.knagmed.clinic.visit.command.VisitCreateCommand;
import com.knagmed.clinic.doctor.DoctorService;
import com.knagmed.clinic.visit.dto.VisitDTO;
import com.knagmed.clinic.entity.Doctor;
import com.knagmed.clinic.entity.Patient;
import com.knagmed.clinic.entity.Visit;
import com.knagmed.clinic.patient.PatientService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class VisitService{

    private final VisitRepository visitRepository;
    private final PatientService patientService;
    private final DoctorService doctorService;

    @Value("${visit.page.size}")
    private Integer visitPageSize;

    public Page<Visit> getByVisitDatePagination(LocalDate localDate, Optional<Integer> page) {
        return visitRepository.findByVisitDate(
                localDate,
                PageRequest.of(
                        page.orElse(0),
                        visitPageSize
                ));
    }

    public Page<VisitDTO> getUpcomingVisits(Optional<Integer> page) {
        Page<Visit> visitPage = visitRepository.findByVisitDateIsGreaterThanEqualAndEndedEquals(
                LocalDate.now(),
                false,
                PageRequest.of(
                        page.orElse(0),
                        visitPageSize
                )
        );
        return mapToVisitDTOsPage(visitPage, visitPage.getPageable(), visitPage.getTotalElements());
    }

    public Page<VisitDTO> getFinishedVisits(Optional<Integer> page) {
        Page<Visit> visitPage = visitRepository.findByVisitDateLessThanOrEnded(
                LocalDate.now(),
                true,
                PageRequest.of(
                        page.orElse(0),
                        visitPageSize
                )
        );
        return mapToVisitDTOsPage(visitPage, visitPage.getPageable(), visitPage.getTotalElements());
    }

    public void deleteVisitById(Long id) {
        visitRepository.deleteById(id);
    }

    public List<Visit> getAll() {
        return visitRepository.findAll();
    }

    @Transactional
    public void addVisit(VisitCreateCommand command) {
        Patient patient = patientService.getPatientByPesel(command.getPatientPesel());
        Doctor doctor = doctorService.getDoctorById(command.getDoctorId());
        Visit visit = new Visit(command.getVisitDate());
        visit.setPatient(patient);
        visit.setDoctor(doctor);
        visitRepository.save(visit);
    }

    public List<VisitDTO> getVisitsByPatientPesel(String pesel) {
        List<Visit> visitByPatient = visitRepository.findVisitByEndedIsFalseAndPatientEquals(pesel);
        return visitByPatient.stream()
                .map(visit -> VisitDTO.builder()
                        .visitId(visit.getId())
                        .visitDate(visit.getVisitDate())
                        .patientData(visit.getPatient().getFirstName() + " " + visit.getPatient().getLastName())
                        .doctorData(visit.getDoctor().getFirstName() + " " + visit.getDoctor().getLastName())
                        .build())
                .collect(Collectors.toList());
    }

    public void setEnded(Long id) {
        visitRepository.setEndedById(id);
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
        return new PageImpl<>(visitDTOList, pageable, totalElements);
    }
}
