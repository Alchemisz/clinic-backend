package com.knagmed.clinic.patient;

import com.knagmed.clinic.patient.dao.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class PeselGenerator {

  private final PatientRepository patientRepository;

  public String generate(LocalDate date) {

    Random random = new Random();

    String year = validateLength(date.getYear() % 100);
    String month = validateLength(date.getMonthValue());
    String day = validateLength(date.getDayOfMonth());

    StringBuilder peselStringBuilder = new StringBuilder();
    peselStringBuilder
        .append(year)
        .append(month)
        .append(day);

    for (int i = 0; i < 5; i++) {
      peselStringBuilder.append(random.nextInt(10));
    }

    String generatedPesel = peselStringBuilder.toString();
    if (patientRepository.existsByPesel(generatedPesel)) {
      generate(date);
    }

    return generatedPesel;
  }

  private String validateLength(int number) {
    StringBuilder stringBuilder = new StringBuilder();
    return number < 10 ? stringBuilder.append("0").append(number).toString() : String.valueOf(number);
  }

}
