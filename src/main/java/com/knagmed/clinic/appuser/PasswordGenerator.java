package com.knagmed.clinic.appuser;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class PasswordGenerator {

  private static final int PASSWORD_LENGTH = 10;

  public String generate(){
    return RandomStringUtils.random(PASSWORD_LENGTH);
  }

}
