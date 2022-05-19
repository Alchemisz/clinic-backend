package com.knagmed.clinic.patient.dao;

import com.knagmed.clinic.entity.Patient;
import com.knagmed.clinic.security.auth.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Page<Patient> findPatientsByPeselGreaterThanEqual(Long pesel, Pageable pageable);
    Page<Patient> findPatientsByPeselEquals(Long pesel, Pageable pageable);

    Patient findPatientByAppUser(AppUser appUser);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(nativeQuery = true, value = "UPDATE PATIENT p SET p.user_id =:userId WHERE p.pesel =:patientPesel")
    void assignUserToPatient(@Param("patientPesel") Long patientPesel, @Param("userId") Long userId);

    boolean existsByPesel(Long pesel);

}
