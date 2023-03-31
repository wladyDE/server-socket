package org.example.authorization.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.authorization.dao.UserDAOImpl;
import org.example.authorization.domain.User;



@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserServiceImpl {
    UserDAOImpl userDAO = new UserDAOImpl();

    public User findUserByNameAndPassword(String name, String password) {
        return userDAO.findUserByNameAndPassword(name, password);
    }
}
