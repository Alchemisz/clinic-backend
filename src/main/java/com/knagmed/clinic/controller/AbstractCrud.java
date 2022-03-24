package com.knagmed.clinic.controller;

import com.knagmed.clinic.customRequest.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface AbstractCrud<T, ID> {

    ResponseEntity<ResponseMessage> delete(ID id);
    ResponseEntity<T> save(@RequestBody T t);
    ResponseEntity<T> getById(ID id);

}
