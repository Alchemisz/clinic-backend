


package com.knagmed.clinic.controller;

import com.knagmed.clinic.customRequest.VisitRequest;
import com.knagmed.clinic.entity.Visit;
import com.knagmed.clinic.service.visit.VisitService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
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

}
