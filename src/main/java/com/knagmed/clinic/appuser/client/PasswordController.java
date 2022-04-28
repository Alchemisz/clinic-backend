package com.knagmed.clinic.appuser.client;

import com.knagmed.clinic.appuser.AppUserPasswordFacade;
import com.knagmed.clinic.appuser.command.PasswordChangeCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/password")
@RequiredArgsConstructor
public class PasswordController {

  private final AppUserPasswordFacade appUserPasswordFacade;

  @GetMapping("/generate")
  public String generatePassword(){
    return appUserPasswordFacade.generatePassword();
  }

  @PatchMapping("/change/{appUserId}")
  public void changePassword(@PathVariable Long appUserId, @RequestBody PasswordChangeCommand command){
    appUserPasswordFacade.changePassword(appUserId, command);
  }

}
