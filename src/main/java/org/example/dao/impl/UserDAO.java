package org.example.dao.impl;

import org.example.dao.IUserDAO;
import org.example.domain.User;

import javax.persistence.*;
import java.util.List;

public class UserDAO implements IUserDAO {

    private EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("auth");

    public UserDAO() {
    }

    public UserDAO(EntityManagerFactory FACTORY) {
        this.FACTORY = FACTORY;
    }

    @Override
    public void insert(User user) {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(user);

        transaction.commit();
        entityManager.close();
    }

    @Override
    public void update(User user) {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        User updatedUser = entityManager.merge(user);
        entityManager.persist(updatedUser);

        transaction.commit();
        entityManager.close();
    }

    @Override
    public User findById(int id) {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        User person = entityManager.find(User.class, id);

        transaction.commit();
        entityManager.close();

        return person;
    }

    @Override
    public void deleteById(int id) {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        User user = entityManager.find(User.class, id);

        entityManager.remove(user);

        transaction.commit();
        entityManager.close();
    }

    @Override
    public List<User> findAll() {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
        List<User> users = query.getResultList();

        transaction.commit();
        entityManager.close();

        return users;
    }

    @Override
    public User findByLogin (String login) {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Query query = entityManager.createQuery("SELECT u FROM User u " +
                "WHERE u.login = :login");
        query.setParameter("login", login);

        List<User> users = query.getResultList();

        transaction.commit();
        entityManager.close();

        if (users.isEmpty()) {
            return null;
        } else {
            return users.get(0);
        }
    }

    @Override
    public long countUserByLogin(String login) {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Query query = entityManager.createQuery("SELECT COUNT(u) FROM User u WHERE u.login = :login");
        query.setParameter("login", login);
        long count = (long) query.getSingleResult();

        transaction.commit();
        entityManager.close();

        return count;
    }

    @Override
    public long countAllUsers() {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Query query = entityManager.createQuery("SELECT COUNT(u) FROM User u");
        long count = (long) query.getSingleResult();

        transaction.commit();
        entityManager.close();

        return count;
    }
}
