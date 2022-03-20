package com.knagmed.clinic.dao;

import com.knagmed.clinic.entity.Visit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {

    Page<Visit> findByVisitDate(LocalDate date, Pageable pageable);

}
