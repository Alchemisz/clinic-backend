package com.knagmed.clinic.visit.client;

import com.knagmed.clinic.visit.command.VisitCreateCommand;
import com.knagmed.clinic.visit.dto.VisitDTO;
import com.knagmed.clinic.entity.Visit;
import com.knagmed.clinic.visit.VisitService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/visit")
@AllArgsConstructor
public class VisitController{

    private final VisitService visitService;

    @GetMapping("/all")
    public List<Visit> getVisits(){
        return visitService.getAll();
    }

    @GetMapping()
    public Page<Visit> getVisitsByDate(@RequestBody LocalDate localDate, @RequestParam Optional<Integer> page){
        return visitService.getByVisitDatePagination(localDate, page);
    }

    @GetMapping("/upcoming")
    public Page<VisitDTO> getUpcomingVisits(@RequestParam Optional<Integer> page){
        return visitService.getUpcomingVisits(page);
    }

    @GetMapping("/finished")
    public Page<VisitDTO> getFinishedVisits(@RequestParam Optional<Integer> page){
        return visitService.getFinishedVisits(page);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        visitService.deleteVisitById(id);
    }

    @PatchMapping("/end/{id}")
    public void setEnded(@PathVariable Long id) {
        visitService.setEnded(id);
    }

    @GetMapping("/upcoming/patient/{pesel}")
    public List<VisitDTO> getPatientsVisits(@PathVariable String pesel){
        return visitService.getVisitsByPatientPesel(pesel);
    }

    @PostMapping("/add")
    public void addVisit(@RequestBody VisitCreateCommand command){
        visitService.addVisit(command);
    }

}
