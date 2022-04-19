package com.knagmed.clinic.dao;

import com.knagmed.clinic.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findPatientByPesel(Long pesel);
    Page<Patient> findPatientsByPeselGreaterThanEqual(Long pesel, Pageable pageable);
    Page<Patient> findPatientsByPeselEquals(Long pesel, Pageable pageable);
}
