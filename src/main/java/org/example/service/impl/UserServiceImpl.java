package org.example.service.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.dao.impl.UserDAOImpl;
import org.example.domain.User;
import org.example.service.UserService;

import java.util.List;


@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {
    UserDAOImpl userDAO = new UserDAOImpl();

    public UserServiceImpl() {
    }

    public UserServiceImpl(UserDAOImpl userDAO) {
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
