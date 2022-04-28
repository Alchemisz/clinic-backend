package com.knagmed.clinic.security.config;

import com.knagmed.clinic.patient.dao.PatientRepository;
import com.knagmed.clinic.security.auth.AppUser;
import com.knagmed.clinic.security.auth.AppUserRepository;
import com.knagmed.clinic.security.auth.AppUserRole;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class UserInit {

    private final PasswordEncoder passwordEncoder;
    private final AppUserRepository appUserRepository;
    private final PatientRepository patientRepository;


    @PostConstruct
    private void initUsers() {
        log.info("USERS INIT");
        AppUser appUser = new AppUser("user", passwordEncoder.encode("password"), List.of(AppUserRole.USER));
        appUserRepository.save(appUser);
        appUserRepository.save(new AppUser("admin", passwordEncoder.encode("password"), List.of(AppUserRole.ADMIN)));
        appUserRepository.save(new AppUser("wikus", passwordEncoder.encode("password"), List.of(AppUserRole.USER)));
        appUserRepository.save(new AppUser("kacpi", passwordEncoder.encode("password"), List.of(AppUserRole.USER)));
        appUserRepository.save(new AppUser("adi", passwordEncoder.encode("password"), List.of(AppUserRole.USER)));


        patientRepository.assignUserToPatient(99143892552L, 1L);
        patientRepository.assignUserToPatient(83113322553L, 2L);
        patientRepository.assignUserToPatient(96213762554L, 3L);
        patientRepository.assignUserToPatient(89154326551L, 4L);
        patientRepository.assignUserToPatient(79133345552L, 5L);

    }
}
