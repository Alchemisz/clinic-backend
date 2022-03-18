package com.knagmed.clinic.config;

import com.knagmed.clinic.entity.Address;
import com.knagmed.clinic.entity.Patient;
import com.knagmed.clinic.service.PatientService;
import org.springframework.stereotype.Component;

@Component
public class DBInit {

    private final PatientService patientService;

    public DBInit(PatientService patientService) {
        this.patientService = patientService;

        initPatient();
    }

    private void initPatient(){
        patientService.save(new Patient(99143892552L,"Adrian", "Rubak", new Address("25-004", "Mójcza", "13A")));
        patientService.save(new Patient(83113322553L,"Karol", "Ziemba", new Address("27-022", "Wrocław", "7B")));
        patientService.save(new Patient(96213762554L,"Bartek", "Jach", new Address("23-002", "Bartków", "2C")));
        patientService.save(new Patient(89154326551L,"Jakub", "Rubak", new Address("23-005", "Warszawa", "14A")));
        patientService.save(new Patient(79133345552L,"Waldemar", "Knaga", new Address("24-014", "Kraków", "2")));

        patientService.save(new Patient(65167345972L,"Jadwikar", "Borcuch", new Address("23-024", "Kielce", "4")));
        patientService.save(new Patient(54123545112L,"Kinga", "Biba", new Address("18-162", "Simlatów", "5")));
    }

}
