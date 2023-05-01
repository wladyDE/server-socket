package org.example.authorization.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.authorization.dao.UserDAOImpl;
import org.example.authorization.domain.User;
import java.util.List;


@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImpl {
    UserDAOImpl userDAO = new UserDAOImpl();

    public UserServiceImpl() {
    }

    public UserServiceImpl(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    public void save(User user) {
        userDAO.save(user);
    }

    public void update(User user) {
        userDAO.update(user);
    }

    public User findById(int id) {
        return userDAO.findById(id);
    }

    public void deleteById(int id) {
        userDAO.deleteById(id);
    }

    public List<User> findAll() {
        return userDAO.findAll();
    }

    public User findByLogin (String login) {
        return userDAO.findByLogin(login);
    }

    public Long countUserByLogin(String login){return userDAO.countUserByLogin(login);}

    public Long countAllUsers(){return userDAO.countAllUsers();}
}
