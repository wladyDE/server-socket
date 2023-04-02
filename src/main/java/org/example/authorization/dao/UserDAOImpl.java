package org.example.authorization.dao;

import javax.persistence.*;

public class UserDAOImpl {

    private EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("auth");

    public UserDAOImpl() {
    }

    public UserDAOImpl(EntityManagerFactory FACTORY) {
        this.FACTORY = FACTORY;
    }

    public long countUsersByNameAndPassword(String name, String password) {
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Query query = em.createQuery("SELECT COUNT(u.name) FROM User u " +
                "WHERE u.name = :name AND u.password = :password");
        query.setParameter("name", name);
        query.setParameter("password", password);

        long result = (long) query.getSingleResult();

        transaction.commit();
        em.close();

        return result;
    }
}
