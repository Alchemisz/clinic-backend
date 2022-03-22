package com.knagmed.clinic.service;

import java.util.List;
import java.util.Optional;

public interface BasicCrudService<T, ID> {

    T save (T t);
    Optional<T> getById(ID id);
    List<T> getAll();
    void deleteById(ID id);
    T update(T t);

}
