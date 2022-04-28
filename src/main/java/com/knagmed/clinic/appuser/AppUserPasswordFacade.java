package com.knagmed.clinic.appuser;

import com.knagmed.clinic.appuser.command.PasswordChangeCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserPasswordFacade {

  private final PasswordGenerator passwordGenerator;
  private final ChangePasswordService changePasswordService;

  public String generatePassword() {
    return passwordGenerator.generate();
  }

  public void changePassword(Long appUserId, PasswordChangeCommand command) {
    changePasswordService.changePasswordForUser(appUserId, command);
  }
}
