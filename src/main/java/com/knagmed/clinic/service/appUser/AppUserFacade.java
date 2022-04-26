package com.knagmed.clinic.service.appUser;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AppUserFacade {

  private final AppUserService appUserService;
  private final LoggedUserService loggedUserService;

}
