package org.example.authorization.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.authorization.dao.UserDAOImpl;


@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImpl {
    UserDAOImpl userDAO = new UserDAOImpl();

    public UserServiceImpl() {
    }

    public UserServiceImpl(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    public long countUsersByNameAndPassword(String name, String password) {
        return userDAO.countUsersByNameAndPassword(name, password);
    }
}
