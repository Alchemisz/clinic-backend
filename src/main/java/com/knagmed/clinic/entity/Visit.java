package com.knagmed.clinic.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
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
    @JoinColumn(name = "patient_pesel")
    private Patient patient;

    @OneToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    public Visit(LocalDate visitDate) {
        this.visitDate = visitDate;
    }
}
