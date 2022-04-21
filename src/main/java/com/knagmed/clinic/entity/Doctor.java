package com.knagmed.clinic.entity;

import com.knagmed.clinic.security.auth.AppUser;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class Doctor extends Person{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToMany
    @JoinTable(
            name = "DOCTOR_SPECIALIZATION",
            joinColumns = {@JoinColumn(name = "DOCTOR_FK")},
            inverseJoinColumns = {@JoinColumn(name = "SPECIALIZATION_FK")})
    private List<Specialization> specializations;

    public Doctor(@NotNull String firstName, @NotNull String lastName, AppUser appUser, Address address, List<Specialization> specializations) {
        super(firstName, lastName, appUser, address);
        this.specializations = specializations;
    }

    public void addSpecialization(Specialization specialization){
        if (this.specializations == null){
            this.specializations = new LinkedList<>();
        }
        this.specializations.add(specialization);
    }
}
