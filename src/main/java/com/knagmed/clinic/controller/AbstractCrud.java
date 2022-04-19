package com.knagmed.clinic.controller;

import com.knagmed.clinic.customRequest.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface AbstractCrud<T, ID> {

    ResponseEntity<T> save(@RequestBody T t);
    ResponseEntity<T> getById(ID id);
    ResponseEntity<T> update(T t);

}
