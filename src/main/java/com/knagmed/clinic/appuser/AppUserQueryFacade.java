package com.knagmed.clinic.appuser;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class AppUserQueryFacade {

  private final AppUserQueryService appUserQueryService;
  private final LoggedUserService loggedUserService;

  public Boolean isUserExists(String message) {
    return appUserQueryService.isUserExists(message);
  }

  public Collection<? extends GrantedAuthority> getLoggedUserRoles() {
    return loggedUserService.getCurrentLoggedUserRoles();
  }

}
