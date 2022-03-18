package com.knagmed.clinic.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface Testable<T>{

    @GetMapping("/test")
    ResponseEntity<T> getTestObject();

}
