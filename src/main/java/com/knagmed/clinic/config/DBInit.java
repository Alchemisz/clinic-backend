package com.knagmed.clinic.config;

import com.knagmed.clinic.entity.*;
import com.knagmed.clinic.service.doctor.DoctorService;
import com.knagmed.clinic.service.patient.PatientService;
import com.knagmed.clinic.service.visit.VisitService;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Component
public class DBInit {

    private final PatientService patientService;
    private final DoctorService doctorService;
    private final VisitService visitService;

    private final List<Patient> patients;
    private final List<Doctor> doctors;
    private final List<Visit> visits;

    public DBInit(PatientService patientService, DoctorService doctorService, VisitService visitService) {
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.visitService = visitService;

        this.patients = new LinkedList<>();
        this.doctors = new LinkedList<>();
        this.visits = new LinkedList<>();

        initPatient();
        initDoctor();
        initVisits();
    }

    private void initVisits() {
        Visit visit = new Visit(LocalDate.of(2021,12,10));
        visit.setDoctor(doctors.get(0));
        visit.setPatient(patients.get(0));
        visitService.save(visit);

        Visit visit2 = new Visit(LocalDate.of(2021,12,10));
        visit2.setDoctor(doctors.get(0));
        visit2.setPatient(patients.get(1));
        visitService.save(visit2);

        Visit visit3 = new Visit(LocalDate.of(2022,1,3));
        visit3.setDoctor(doctors.get(0));
        visit3.setPatient(patients.get(2));
        visitService.save(visit3);
    }

    private void initDoctor() {
        Doctor doctor = new Doctor("Wacław", "Laczek", new Address("24-023", "Szczeciń", "25B"));
        doctor.addSpecialization(new Specialization("Kardiolog"));
        doctor.addSpecialization(new Specialization("Stomatolog"));
        doctors.add(doctorService.save(doctor));
    }

    private void initPatient(){
        patients.add(patientService.save(new Patient(99143892552L,"Adrian", "Rubak", new Address("25-004", "Mójcza", "13A"))));
        patients.add(patientService.save(new Patient(83113322553L,"Karol", "Ziemba", new Address("27-022", "Wrocław", "7B"))));
        patients.add(patientService.save(new Patient(96213762554L,"Bartek", "Jach", new Address("23-002", "Bartków", "2C"))));
        patients.add(patientService.save(new Patient(89154326551L,"Jakub", "Rubak", new Address("23-005", "Warszawa", "14A"))));
        patients.add(patientService.save(new Patient(79133345552L,"Waldemar", "Knaga", new Address("24-014", "Kraków", "2"))));
        patients.add(patientService.save(new Patient(65167345972L,"Jadwiga", "Borcuch", new Address("23-024", "Kielce", "4"))));
        patients.add(patientService.save(new Patient(54123545112L,"Kinga", "Biba", new Address("18-162", "Simlatów", "5"))));
        patients.add(patientService.save(new Patient(97653545112L,"Amelia", "Kora", new Address("23-162", "Gorzałkowice", "2"))));

        patients.add(patientService.save(new Patient(94231294552L,"Marcin", "Waldon", new Address("18-162", "Simlatów", "4"))));
        patients.add(patientService.save(new Patient(89432294552L,"Arkadiusz", "Psitek", new Address("18-162", "Simlatów", "32"))));
        patients.add(patientService.save(new Patient(83232143552L,"Bożena", "Pawlak", new Address("18-162", "Simlatów", "12A"))));
        patients.add(patientService.save(new Patient(43419543552L,"Paweł", "Oracz", new Address("18-162", "Simlatów", "11A"))));
        patients.add(patientService.save(new Patient(89239523256L,"Aleksander", "Knut", new Address("18-162", "Simlatów", "11C"))));
        patients.add(patientService.save(new Patient(24241163552L,"Barbara", "Rudzka", new Address("18-163", "Borcuchów", "23D"))));
        patients.add(patientService.save(new Patient(21159643552L,"Mateusz", "Bodzon", new Address("18-162", "Simlatów", "27A"))));
        patients.add(patientService.save(new Patient(98123023372L,"Kacper", "Diler", new Address("18-163", "Borcuchów", "31C"))));

        patients.add(patientService.save(new Patient(67431235673L,"Jakub", "Stawiarz", new Address("08-101", "Oziemiki", "16"))));
        patients.add(patientService.save(new Patient(89245872712L,"Daniel", "Kot", new Address("18-163", "Borcuchów", "3C"))));
    }

}
