package com.knagmed.clinic.entity;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "DOCTOR_SPECIALIZATION",
            joinColumns = {@JoinColumn(name = "DOCTOR_FK")},
            inverseJoinColumns = {@JoinColumn(name = "SPECIALIZATION_FK")})
    private List<Specialization> specializations;

    public Doctor(@NotNull String firstName, @NotNull String lastName, Address address) {
        super(firstName, lastName, address);
    }

    public void addSpecialization(Specialization specialization){
        if (this.specializations == null){
            this.specializations = new LinkedList<>();
        }
        this.specializations.add(specialization);
    }
}
