package org.example.authorization.service;

import org.example.authorization.dao.UserDAOImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserDAOImpl userDAO;

    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userDAO);
    }

/*    @Test
    void countUsersByNameAndPasswordTest_expect1() {
        when(userService.countUsersByLogin(anyString(), anyString())).thenReturn(1L);
        Long result = userService.countUsersByLogin("Anton", "1234");

        assertEquals(1L, result);
    }*/
}