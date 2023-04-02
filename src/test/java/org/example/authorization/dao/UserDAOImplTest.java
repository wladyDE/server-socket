package org.example.authorization.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserDAOImplTest {
    @Mock
    EntityManagerFactory factory;

    @Mock
    EntityManager em;

    @Mock
    Query query;

    @Mock
    EntityTransaction transaction;

    private UserDAOImpl userDAO;

    @BeforeEach
    void setUp() {
        doNothing().when(em).close();
        doNothing().when(transaction).commit();
        when(query.setParameter(anyString(), anyString())).thenReturn(query);
        when(em.createQuery(anyString())).thenReturn(query);
        doNothing().when(transaction).begin();
        when(em.getTransaction()).thenReturn(transaction);
        when(factory.createEntityManager()).thenReturn(em);

        userDAO = new UserDAOImpl(factory);
    }

    @Test
    void countUsersByNameAndPasswordTest_expect1() {

        when(query.getSingleResult()).thenReturn(1L);
        Long expectedUserCount = userDAO.countUsersByNameAndPassword("Anton", "1234");

        assertEquals(expectedUserCount, 1L);
    }

    @Test
    void countUsersByNameAndPasswordTest_expect0() {

        when(query.getSingleResult()).thenReturn(0L);
        Long expectedUserCount = userDAO.countUsersByNameAndPassword("Anton", "1234");

        assertEquals(expectedUserCount, 0L);
    }
}