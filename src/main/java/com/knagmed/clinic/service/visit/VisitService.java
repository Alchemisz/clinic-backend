package com.knagmed.clinic.service.visit;

import com.knagmed.clinic.customRequest.VisitRequest;
import com.knagmed.clinic.dao.VisitRepository;
import com.knagmed.clinic.entity.Visit;
import com.knagmed.clinic.service.BasicCrudService;
import com.knagmed.clinic.service.BasicCrudServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.Optional;

public abstract class VisitService extends BasicCrudServiceImpl<Visit, Long, VisitRepository> {

    public VisitService(VisitRepository repository) {
        super(repository);
    }

    public abstract Visit save(VisitRequest visitRequest);

    public abstract Page<Visit> getByVisitDatePagination(LocalDate localDate, Optional<Integer> page);
}
