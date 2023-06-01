package org.example.service;

import java.util.List;

public interface CRUDService<T> {
    void save(T t);
    T findById(int id);
    void update(T t);
    void deleteById(int id);
    List<T> findAll();
}
