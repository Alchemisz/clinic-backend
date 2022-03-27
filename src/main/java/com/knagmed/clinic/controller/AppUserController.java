package com.knagmed.clinic.controller;

import com.knagmed.clinic.customRequest.Message;
import com.knagmed.clinic.service.appUser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;

    @PostMapping("/exists")
    public ResponseEntity<Message> isUserExists(@RequestBody Message requestMessage){
        Boolean userExists = appUserService.isUserExists(requestMessage.getMessage());
        return new ResponseEntity<>(new Message(userExists.toString()), userExists ? HttpStatus.FOUND : HttpStatus.NOT_FOUND);
    }

}
