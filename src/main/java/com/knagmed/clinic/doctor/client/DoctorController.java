package com.knagmed.clinic.doctor.client;

import com.knagmed.clinic.doctor.DoctorService;
import com.knagmed.clinic.doctor.command.CreateDoctorCommand;
import com.knagmed.clinic.doctor.command.UpdateDoctorCommand;
import com.knagmed.clinic.doctor.dto.DoctorDTO;
import com.knagmed.clinic.entity.Doctor;
import com.knagmed.clinic.entity.Specialization;
import com.knagmed.clinic.exception.ApiRequestException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/doctor")
@AllArgsConstructor
//@PreAuthorize("hasRole('ROLE_ADMIN')")
public class DoctorController{

    private final DoctorService doctorService;

    @PostMapping
    public Doctor addDoctor(@Valid @RequestBody CreateDoctorCommand command){
        return doctorService.save(command);
    }

    @GetMapping("/{id}")
    public Doctor getById(@PathVariable Long id){
        return doctorService.getDoctorById(id);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable Long id, @Valid @RequestBody UpdateDoctorCommand command) {
        doctorService.updateDoctor(command, id);
    }

    @GetMapping("/pageable")
    public Page<Doctor> getPatients(@RequestParam Optional<Integer> page, @RequestParam Optional<String> sortBy){
        return doctorService.getByPagination(page, sortBy);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        doctorService.deleteDoctorAndVisitsByDoctorId(id);
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
