package com.knagmed.clinic.controller;

import com.knagmed.clinic.customRequest.Message;
import com.knagmed.clinic.dto.DoctorDTO;
import com.knagmed.clinic.entity.Doctor;
import com.knagmed.clinic.entity.Patient;
import com.knagmed.clinic.entity.Specialization;
import com.knagmed.clinic.service.doctor.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/pageable")
    public Page<Doctor> getPatients(@RequestParam Optional<Integer> page, @RequestParam Optional<String> sortBy){
        return service.getByPagination(page, sortBy);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> delete(@PathVariable Long id) {
        service.deleteDoctorAndVisitsByDoctorId(id);
        return new ResponseEntity<>(new Message("Deleted correctly!"), HttpStatus.OK);
    }

    @GetMapping("/specializations")
    public List<Specialization> getAllSpecializations(){
        return service.getAllSpecializations();
    }

    @GetMapping("/all")
    public List<DoctorDTO> getAllDoctors(){
        return  service.getAllDoctors();
    }

}
