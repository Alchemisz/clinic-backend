package com.knagmed.clinic.client;

import com.knagmed.clinic.customRequest.Message;
import com.knagmed.clinic.dto.DoctorDTO;
import com.knagmed.clinic.entity.Doctor;
import com.knagmed.clinic.entity.Specialization;
import com.knagmed.clinic.exception.ApiRequestException;
import com.knagmed.clinic.service.doctor.DoctorService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doctor")
@AllArgsConstructor
//@PreAuthorize("hasRole('ROLE_ADMIN')")
public class DoctorController{

    private final DoctorService doctorService;

    @RequestMapping(method = {RequestMethod.POST})
    public ResponseEntity<Doctor> save(@RequestBody Doctor t){
        Doctor save = doctorService.save(t);
        HttpStatus responseCode = save == null ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED;
        return new ResponseEntity<>(save, responseCode);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getById(@PathVariable Long id){
        Doctor doctor = doctorService.getDoctorById(id);
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Doctor> update(@RequestBody Doctor t) {
        throw new ApiRequestException("This method is not implemented yet!");
    }

    @GetMapping("/pageable")
    public Page<Doctor> getPatients(@RequestParam Optional<Integer> page, @RequestParam Optional<String> sortBy){
        return doctorService.getByPagination(page, sortBy);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message> delete(@PathVariable Long id) {
        doctorService.deleteDoctorAndVisitsByDoctorId(id);
        return new ResponseEntity<>(new Message("Deleted correctly!"), HttpStatus.OK);
    }

    @GetMapping("/specializations")
    public List<Specialization> getAllSpecializations(){
        return doctorService.getAllSpecializations();
    }

    @GetMapping("/all")
    public List<DoctorDTO> getAllDoctors(){
        return  doctorService.getAllDoctors();
    }

}
