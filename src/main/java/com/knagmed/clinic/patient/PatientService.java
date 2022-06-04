package com.knagmed.clinic.patient;

import com.knagmed.clinic.appuser.LoggedUserService;
import com.knagmed.clinic.common.AddressRepository;
import com.knagmed.clinic.entity.Address;
import com.knagmed.clinic.entity.Patient;
import com.knagmed.clinic.patient.client.command.CreatePatientCommand;
import com.knagmed.clinic.patient.client.command.UpdatePatientCommand;
import com.knagmed.clinic.patient.dao.PatientRepository;
import com.knagmed.clinic.security.auth.AppUser;
import com.knagmed.clinic.security.auth.AppUserRepository;
import com.knagmed.clinic.security.auth.AppUserRole;
import com.knagmed.clinic.visit.dao.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final VisitRepository visitRepository;
    private final LoggedUserService loggedUserService;
    private final PatientRepository patientRepository;
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AddressRepository addressRepository;

    @Value("${patient.page.size}")
    private Integer patientPageSize;

    @Value("${patient.default.sort.field}")
    private String patientDefaultSortField;

    public Page<Patient> getByPagination(Optional<Integer> page, Optional<String> value, Optional<String> sortBy) {

        PageRequest paginationSetup = PageRequest.of(
            page.orElse(0),
            patientPageSize,
            Sort.Direction.ASC, sortBy.orElse(patientDefaultSortField)
        );
        if (value.isPresent()) {
            String searchPhrase = value.get();
            if (searchPhrase.length() == 11)
                return patientRepository.findPatientsByPeselEquals(searchPhrase, paginationSetup);
            return patientRepository.findPatientsByPeselGreaterThanEqual(searchPhrase + "0".repeat(11 - searchPhrase.length()), paginationSetup);
        }

        return patientRepository.findAll(paginationSetup);
    }

    @Transactional
    public void deletePatientAndRelatedVisitdByPesel(String patientPesel) {
        visitRepository.deleteAllByPatientPesel(patientPesel);
        patientRepository.deleteById(patientPesel);
    }

    public String getUserPesel() {
        AppUser appUser = loggedUserService.getCurrentLoggedUser();
        Patient patientByAppUser = patientRepository.findPatientByAppUser(appUser);
        return patientByAppUser.getPesel();
    }

    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    public Patient getPatientByPesel(String pesel) {
        return patientRepository.findById(pesel).
            orElseThrow(() -> new IllegalArgumentException(String.format("Can not find patient with ID = %d", pesel)));
    }

    @Transactional
    public void save(CreatePatientCommand command) {
        AppUser appUser = appUserRepository.save(new AppUser(command.getUsername(), passwordEncoder.encode(command.getPassword()), List.of(AppUserRole.USER)));
        Address address = addressRepository.save(new Address(command.getAddress().getPostCode(), command.getAddress().getCity(), command.getAddress().getHouseNumber()));
        Patient patient = new Patient(command.getFirstName(), command.getLastName(), appUser,
            address, command.getPesel());
        patientRepository.save(patient);
    }

    @Transactional
    public void updatePatient(UpdatePatientCommand command) {
        Patient patientByPesel = patientRepository.findPatientByPesel(command.getPesel());
        Address address = patientByPesel.getAddress();
        patientByPesel.setFirstName(command.getFirstName());
        patientByPesel.setLastName(command.getLastName());
        address.setCity(command.getCity());
        address.setHouseNumber(command.getHomeNumber());
        address.setPostCode(command.getPostCode());
        addressRepository.save(address);
        patientRepository.save(patientByPesel);

    }
}
