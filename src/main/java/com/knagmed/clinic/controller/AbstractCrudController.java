package com.knagmed.clinic.controller;

import com.knagmed.clinic.customRequest.ResponseMessage;
import com.knagmed.clinic.service.BasicCrudService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.core.CrudMethods;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@AllArgsConstructor
public class AbstractCrudController<T, ID, S extends BasicCrudService<T, ID>> implements AbstractCrud<T,ID> {

    protected S service;

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<ResponseMessage> delete(@PathVariable ID id) {
        service.deleteById(id);
        return new ResponseEntity<>(new ResponseMessage("Deleted correctly!"), HttpStatus.OK);
    }
}
