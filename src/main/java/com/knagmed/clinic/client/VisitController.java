


package com.knagmed.clinic.client;

import com.knagmed.clinic.customRequest.VisitRequest;
import com.knagmed.clinic.client.command.VisitCreateCommand;
import com.knagmed.clinic.dto.VisitDTO;
import com.knagmed.clinic.entity.Visit;
import com.knagmed.clinic.service.visit.VisitService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/visit")
@AllArgsConstructor
public class VisitController{

    private final VisitService visitService;

    @PostMapping
    public ResponseEntity<Visit> addVisit(@RequestBody VisitRequest visitRequest){
        Visit save = visitService.save(visitRequest);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Visit>> getVisits(){
        List<Visit> all = visitService.getAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
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
    public List<VisitDTO> getPatientsVisits(@PathVariable Long pesel){
        return visitService.getVisitsByPatientPesel(pesel);
    }

    @PostMapping("/add")
    public void addVisit(@RequestBody VisitCreateCommand command){
        visitService.addVisit(command);
    }

}
