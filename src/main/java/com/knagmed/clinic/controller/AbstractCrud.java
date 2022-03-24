package com.knagmed.clinic.controller;

import com.knagmed.clinic.customRequest.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface AbstractCrud<T, ID> {

    ResponseEntity<ResponseMessage> delete(ID id);

}
