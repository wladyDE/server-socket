package org.example.service.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.dao.impl.UserDAO;
import org.example.domain.User;
import org.example.service.IUserService;

import java.util.List;


@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserService implements IUserService {
    UserDAO userDAO = new UserDAO();

    public UserService() {
    }

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void save(User user) {
        userDAO.insert(user);
    }

    @Override
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    public User findById(int id) {
        return userDAO.findById(id);
    }

    @Override
    public void deleteById(int id) {
        userDAO.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public User findByLogin (String login) {
        return userDAO.findByLogin(login);
    }

    @Override
    public Long countUserByLogin(String login){return userDAO.countUserByLogin(login);}

    @Override
    public Long countAllUsers(){return userDAO.countAllUsers();}
}
