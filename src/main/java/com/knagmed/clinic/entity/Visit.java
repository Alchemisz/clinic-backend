package com.knagmed.clinic.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@Data
@Entity
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    private LocalDate visitDate;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE) //calls trigger which after deleting patient deletes also a visit
    @JoinColumn(name = "patient_pesel")
    private Patient patient;

    @OneToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    private boolean isEnded;

    public Visit(LocalDate visitDate) {
        this.visitDate = visitDate;
    }
}
