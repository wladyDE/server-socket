package org.example.service;

import org.example.domain.User;

public interface IUserService extends ICRUDService<User> {
    User findByLogin (String login);
    Long countUserByLogin(String login);
    Long countAllUsers();

}