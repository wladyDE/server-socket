package org.example.authentication.service;

import org.example.dao.impl.UserDAOImpl;
import org.example.domain.User;
import org.example.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserDAOImpl userDAO;

    private UserServiceImpl userService;

    private final User user = User.builder()
            .login("Wlady")
            .password("1234")
            .build();

    @BeforeEach
    void setUp() {
        userService = new UserServiceImpl(userDAO);
    }

    @Test
    void saveTest() {
        doNothing().when(userDAO).insert(user);

        userService.save(user);

        verify(userDAO).insert(user);
    }

    @Test
    void updateTest() {
        doNothing().when(userDAO).update(user);

        userService.update(user);

        verify(userDAO).update(user);
    }

    @Test
    void findByIdTest() {
        when(userDAO.findById(1)).thenReturn(user);

        User newUser = userService.findById(1);

        assertAll(
                () -> assertEquals(user.getLogin() ,newUser.getLogin(), "Incorrect Login"),
                () -> assertEquals(user.getPassword(), newUser.getPassword(), "Incorrect Password")
        );
    }

    @Test
    void deleteByIdTest () {
        doNothing().when(userDAO).deleteById(anyInt());

        userService.deleteById(1);

        verify(userDAO).deleteById(1);
    }

    @Test
    void findAllTest () {
        List<User> users = List.of(user, user);

        when(userDAO.findAll()).thenReturn(users);

        List<User> actualUsers = userService.findAll();

        assertEquals(users.size(), actualUsers.size());
        assertTrue(users.containsAll(actualUsers) && actualUsers.containsAll(users));
    }

    @Test
    void findByLoginTest () {
        when(userDAO.findByLogin(anyString())).thenReturn(user);

        User newUser = userService.findByLogin("Wlady");

        assertAll(
                () -> assertEquals(user.getLogin() ,newUser.getLogin(), "Incorrect Login"),
                () -> assertEquals(user.getPassword(), newUser.getPassword(), "Incorrect Password")
        );
    }

    @Test
    void countUserByLoginTest(){
        when(userDAO.countUserByLogin(anyString())).thenReturn(1L);

        assertEquals(1L, userService.countUserByLogin("Wlady"));
    }

    @Test
    void countAllUsers() {
        when(userDAO.countAllUsers()).thenReturn(1L);

        assertEquals(1L, userService.countAllUsers());
    }
}