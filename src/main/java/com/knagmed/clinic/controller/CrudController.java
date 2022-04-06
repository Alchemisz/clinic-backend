package com.knagmed.clinic.controller;

import com.knagmed.clinic.customRequest.Message;
import com.knagmed.clinic.exception.ApiRequestException;
import com.knagmed.clinic.service.BasicCrudService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@AllArgsConstructor
public class CrudController<T, ID, S extends BasicCrudService<T, ID>> implements AbstractCrud<T,ID> {

    protected S service;

    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Message> delete(@PathVariable ID id) {
        service.deleteById(id);
        return new ResponseEntity<>(new Message("Deleted correctly!"), HttpStatus.OK);
    }

    @RequestMapping(method = {RequestMethod.POST})
    @Override
    public ResponseEntity<T> save(@RequestBody T t){
        T save = service.save(t);
        HttpStatus responseCode = save == null ? HttpStatus.BAD_REQUEST : HttpStatus.CREATED;
        return new ResponseEntity<>(save, responseCode);
    }

    @GetMapping("/{id}")
    @Override
    public ResponseEntity<T> getById(@PathVariable ID id){
        Optional<T> optGet = service.getById(id);
        return optGet.map(
                optionalGet -> new ResponseEntity<>(optionalGet, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PutMapping
    @Override
    public ResponseEntity<T> update(@RequestBody T t) {
        throw new ApiRequestException("This method is not implemented yet!");
    }
}