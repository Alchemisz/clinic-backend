package com.knagmed.clinic.appuser.client;

import com.knagmed.clinic.customRequest.Message;
import com.knagmed.clinic.appuser.AppUserQueryFacade;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class AppUserController {

    private final AppUserQueryFacade appUserQueryFacade;

    @PostMapping("/exists")
    public Message isUserExists(@RequestBody Message requestMessage){
        Boolean userExists = appUserQueryFacade.isUserExists(requestMessage.getMessage());
        return new Message(userExists.toString());
    }

    @GetMapping("/roles")
    public Collection<? extends GrantedAuthority> getUserRole(){
        return appUserQueryFacade.getLoggedUserRoles();
    }

}
