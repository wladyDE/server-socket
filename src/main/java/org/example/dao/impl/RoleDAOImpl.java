package org.example.dao.impl;

import org.example.dao.CRUDDAO;
import org.example.domain.Role;

import javax.persistence.*;
import java.util.List;

public class RoleDAOImpl implements CRUDDAO<Role> {
    private final EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("auth");

    @Override
    public void insert(Role role) {
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(role);

        transaction.commit();
        em.close();
    }

    @Override
    public void update(Role role) {
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Role updatedRole = em.merge(role);
        em.persist(updatedRole);

        transaction.commit();
        em.close();
    }

    @Override
    public Role findById(int id) {
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Role role = em.find(Role.class, id);

        transaction.commit();
        em.close();

        return role;
    }

    @Override
    public void deleteById(int id) {
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        Role role = em.find(Role.class, id);

        em.remove(role);

        transaction.commit();
        em.close();
    }

    @Override
    public List<Role> findAll() {
        EntityManager em = FACTORY.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        TypedQuery<Role> query = em.createQuery("SELECT u FROM Role u", Role.class);
        List<Role> roles = query.getResultList();

        transaction.commit();
        em.close();

        return roles;
    }
}
