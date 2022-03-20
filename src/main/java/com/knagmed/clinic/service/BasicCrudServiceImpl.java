package com.knagmed.clinic.service;

import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class BasicCrudServiceImpl<T, ID, R extends JpaRepository<T, ID>> implements BasicCrudService<T, ID>{

    protected R repository;

    @Override
    public T save(T t) {
        return repository.save(t);
    }

    @Override
    public Optional<T> getById(ID id) {
        return repository.findById(id);
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }
}
