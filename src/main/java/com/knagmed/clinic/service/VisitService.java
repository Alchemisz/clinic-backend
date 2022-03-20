package com.knagmed.clinic.service;

import com.knagmed.clinic.customRequest.VisitRequest;
import com.knagmed.clinic.entity.Visit;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.Optional;

public interface VisitService extends BasicCrudService<Visit, Long> {

    Visit save(VisitRequest visitRequest);

    Page<Visit> getByVisitDatePagination(LocalDate localDate, Optional<Integer> page);
}
