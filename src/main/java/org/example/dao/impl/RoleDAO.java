package org.example.dao.impl;

import org.example.dao.ICRUDDAO;
import org.example.domain.Role;

import javax.persistence.*;
import java.util.List;

public class RoleDAO implements ICRUDDAO<Role> {
    private final EntityManagerFactory FACTORY =
            Persistence.createEntityManagerFactory("auth");

    @Override
    public void insert(Role role) {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(role);

        transaction.commit();
        entityManager.close();
    }

    @Override
    public void update(Role role) {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Role updatedRole = entityManager.merge(role);
        entityManager.persist(updatedRole);

        transaction.commit();
        entityManager.close();
    }

    @Override
    public Role findById(int id) {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Role role = entityManager.find(Role.class, id);

        transaction.commit();
        entityManager.close();

        return role;
    }

    @Override
    public void deleteById(int id) {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Role role = entityManager.find(Role.class, id);

        entityManager.remove(role);

        transaction.commit();
        entityManager.close();
    }

    @Override
    public List<Role> findAll() {
        EntityManager entityManager = FACTORY.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TypedQuery<Role> query = entityManager.createQuery("SELECT u FROM Role u", Role.class);
        List<Role> roles = query.getResultList();

        transaction.commit();
        entityManager.close();

        return roles;
    }
}
