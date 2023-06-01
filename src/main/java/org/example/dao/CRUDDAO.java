package org.example.dao;

import java.util.List;

public interface CRUDDAO<T> {
    void insert(T t);
    T findById(int id);
    void update(T t);
    void deleteById(int id);
    List<T> findAll();
}
