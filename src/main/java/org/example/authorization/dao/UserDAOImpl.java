package org.example.authorization.dao;

import org.example.authorization.domain.User;

import javax.persistence.*;

public class UserDAOImpl {
    public static final EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("auth");

    public User findUserByNameAndPassword(String name, String password) {
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        TypedQuery<User> query = em.createQuery("SELECT u FROM User u " +
                "WHERE u.name = :name AND u.password = :password", User.class);
        query.setParameter("name", name);
        query.setParameter("password", password);

        User user;
        try {
            user = query.getSingleResult();
        } catch (NoResultException e) {
            user = null;
        }

        transaction.commit();
        em.close();

        return user;
    }
}
