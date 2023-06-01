package org.example.dao;

import org.example.dao.CRUDDAO;
import org.example.domain.User;

import java.time.LocalDate;
import java.util.List;

public interface UserDAO extends CRUDDAO<User> {
    User findByLogin (String login);
    long countUserByLogin(String login);
    long countAllUsers();

}