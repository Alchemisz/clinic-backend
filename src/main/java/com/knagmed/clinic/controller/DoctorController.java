package com.knagmed.clinic.controller;

import com.knagmed.clinic.entity.Doctor;
import com.knagmed.clinic.service.doctor.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;

@RestController
@RequestMapping("/doctor")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
public class DoctorController extends CrudController<Doctor, Long, DoctorService>{

    public DoctorController(DoctorService service) {
        super(service);
    }

    @GetMapping("")
    public ResponseEntity<List<Doctor>> getDoctor(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

}
