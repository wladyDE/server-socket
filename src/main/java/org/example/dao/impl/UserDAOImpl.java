package org.example.dao.impl;

import org.example.dao.UserDAO;
import org.example.domain.User;

import javax.persistence.*;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("auth");

    public UserDAOImpl() {
    }

    public UserDAOImpl(EntityManagerFactory FACTORY) {
        this.FACTORY = FACTORY;
    }

    @Override
    public void insert(User user) {
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(user);

        transaction.commit();
        em.close();
    }

    @Override
    public void update(User user) {
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        User updatedUser = em.merge(user);
        em.persist(updatedUser);

        transaction.commit();
        em.close();
    }

    @Override
    public User findById(int id) {
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        User person = em.find(User.class, id);

        transaction.commit();
        em.close();

        return person;
    }

    @Override
    public void deleteById(int id) {
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        User user = em.find(User.class, id);

        em.remove(user);

        transaction.commit();
        em.close();
    }

    @Override
    public List<User> findAll() {
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
        List<User> users = query.getResultList();

        transaction.commit();
        em.close();

        return users;
    }

    @Override
    public User findByLogin (String login) {
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Query query = em.createQuery("SELECT u FROM User u " +
                "WHERE u.login = :login");
        query.setParameter("login", login);

        List<User> users = query.getResultList();

        transaction.commit();
        em.close();

        if (users.isEmpty()) {
            return null;
        } else {
            return users.get(0);
        }
    }

    @Override
    public long countUserByLogin(String login) {
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Query query = em.createQuery("SELECT COUNT(u) FROM User u WHERE u.login = :login");
        query.setParameter("login", login);
        long count = (long) query.getSingleResult();

        transaction.commit();
        em.close();

        return count;
    }

    @Override
    public long countAllUsers() {
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Query query = em.createQuery("SELECT COUNT(u) FROM User u");
        long count = (long) query.getSingleResult();

        transaction.commit();
        em.close();

        return count;
    }
}
