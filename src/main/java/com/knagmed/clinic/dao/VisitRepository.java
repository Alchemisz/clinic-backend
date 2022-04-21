package com.knagmed.clinic.dao;

import com.knagmed.clinic.entity.Visit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

    Page<Visit> findByVisitDate(LocalDate date, Pageable pageable);

    Page<Visit> findByVisitDateIsGreaterThanEqual(LocalDate date, Pageable pageable);

    Page<Visit> findByVisitDateLessThan(LocalDate date, Pageable pageable);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM VISIT v WHERE v.patient_pesel = :pesel")
    void deleteAllByPatientPesel(@Param("pesel") Long pesel);

    @Modifying
    @Query(nativeQuery = true, value = "DELETE FROM VISIT v WHERE v.doctor_id = :doctorId")
    void deleteAllByDoctorId(@Param("doctorId") Long doctorId);

}
