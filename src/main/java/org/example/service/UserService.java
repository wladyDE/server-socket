package org.example.service;

import org.example.dao.CRUDDAO;
import org.example.domain.User;

public interface UserService extends CRUDService<User> {
    User findByLogin (String login);
    Long countUserByLogin(String login);
    Long countAllUsers();

}