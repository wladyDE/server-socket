package org.example.dao;

import org.example.domain.User;

public interface IUserDAO extends ICRUDDAO<User> {
    User findByLogin (String login);
    long countUserByLogin(String login);
    long countAllUsers();

}