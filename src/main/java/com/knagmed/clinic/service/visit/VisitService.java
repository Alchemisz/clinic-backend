package com.knagmed.clinic.service.visit;

import com.knagmed.clinic.dao.VisitRepository;
import com.knagmed.clinic.client.command.VisitCreateCommand;
import com.knagmed.clinic.dto.VisitDTO;
import com.knagmed.clinic.entity.Visit;
import com.knagmed.clinic.service.BasicCrudServiceImpl;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public abstract class VisitService extends BasicCrudServiceImpl<Visit, Long, VisitRepository> {

    public VisitService(VisitRepository repository) {
        super(repository);
    }

    public abstract Page<Visit> getByVisitDatePagination(LocalDate localDate, Optional<Integer> page);

    public abstract Page<VisitDTO> getUpcomingVisits(Optional<Integer> page);

    public abstract Page<VisitDTO> getFinishedVisits(Optional<Integer> page);

    public abstract void deleteVisitById(Long id);

    public abstract void addVisit(VisitCreateCommand command);

    public abstract List<VisitDTO> getVisitsByPatientPesel(Long pesel);

    public abstract void setEnded(Long id);
}
