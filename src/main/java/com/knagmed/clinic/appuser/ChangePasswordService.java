package com.knagmed.clinic.appuser;

import com.knagmed.clinic.appuser.command.PasswordChangeCommand;
import com.knagmed.clinic.appuser.exceptions.AppUserNotFoundException;
import com.knagmed.clinic.appuser.exceptions.PasswordAreDifferentException;
import com.knagmed.clinic.security.auth.AppUser;
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
    AppUser appUser = appUserRepository.findById(appUserId)
        .orElseThrow(() -> new AppUserNotFoundException(String.format("AppUser with ID = %d not found", appUserId)));
    if (isAppUserPasswordSameAs(appUser.getPassword(), command.getOldPassword())) {
      appUser.setPassword(passwordEncoder.encode(command.getNewPassword()));
      appUserRepository.save(appUser);
      return;
    }
    throw new PasswordAreDifferentException(String.format("Password of user with ID = %d is not same as passed", appUserId));
  }

  private boolean isAppUserPasswordSameAs(String userPassword, String oldPassword) {
    return passwordEncoder.matches(oldPassword, userPassword);
  }

}
