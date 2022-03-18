package com.knagmed.clinic.dao;

import com.knagmed.clinic.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findPatientByPesel(Long pesel);

}
