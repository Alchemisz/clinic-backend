package com.knagmed.clinic.visit.dao;

import com.knagmed.clinic.entity.Visit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

    Page<Visit> findByVisitDate(LocalDate date, Pageable pageable);

    @Query(value = "SELECT v FROM Visit v WHERE v.visitDate >= :date AND v.isEnded = :ended  ")
    Page<Visit> findByVisitDateIsGreaterThanEqualAndEndedEquals(@Param("date") LocalDate date, @Param("ended") Boolean ended, Pageable pageable);

    @Query(value = "SELECT v FROM Visit v WHERE v.visitDate < :date OR v.isEnded = :ended  ")
    Page<Visit> findByVisitDateLessThanOrEnded(@Param("date") LocalDate date, @Param("ended") Boolean ended, Pageable pageable);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM VISIT v WHERE v.patient_pesel = :pesel")
    void deleteAllByPatientPesel(@Param("pesel") String pesel);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM VISIT v WHERE v.doctor_id = :doctorId")
    void deleteAllByDoctorId(@Param("doctorId") Long doctorId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Visit v set v.isEnded = true where v.id = :visitId")
    void setEndedById(@Param("visitId") Long visitId);

    @Query(value = "SELECT v FROM Visit v WHERE v.patient.pesel = :patientPesel AND v.isEnded = false")
    List<Visit> findVisitByEndedIsFalseAndPatientEquals(@Param("patientPesel") Long patientPesel);
}
