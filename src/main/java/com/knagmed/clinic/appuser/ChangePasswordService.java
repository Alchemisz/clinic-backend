package com.knagmed.clinic.appuser;

import com.knagmed.clinic.appuser.command.PasswordChangeCommand;
import com.knagmed.clinic.appuser.exceptions.AppUserNotFoundException;
import com.knagmed.clinic.appuser.exceptions.PasswordAreDifferentException;
import com.knagmed.clinic.security.auth.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChangePasswordService {

  private final PasswordEncoder passwordEncoder;
  private final AppUserRepository appUserRepository;

  public void changePasswordForUser(Long appUserId, PasswordChangeCommand command) {
    String userPassword = appUserRepository.findById(appUserId)
        .orElseThrow(() -> new AppUserNotFoundException(String.format("AppUser with ID = %d not found", appUserId)))
        .getPassword();
    if (isAppUserPasswordSameAs(userPassword, command.getOldPassword())){
      appUserRepository.updateUserPassword(command.getNewPassword(), appUserId);
    }
    throw new PasswordAreDifferentException(String.format("Password of user with ID = %d is not same as passed", appUserId));
  }

  private boolean isAppUserPasswordSameAs(String userPassword, String oldPassword){
    return passwordEncoder.encode(oldPassword).equals(userPassword);
  }

}
