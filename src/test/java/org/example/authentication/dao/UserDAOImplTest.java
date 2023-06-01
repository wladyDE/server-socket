package org.example.authentication.dao;

import org.example.dao.impl.UserDAOImpl;
import org.example.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.Persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDAOImplTest {
    private final UserDAOImpl userDAO =
            new UserDAOImpl(Persistence.createEntityManagerFactory("auth_test"));
    private static final String LOGIN = "Wlady";
    private static final String PASSWORD = "3zeNpPg/lxp+ORtpoM2Cxw==";

    @BeforeEach
    void setUp() throws Exception {
        truncateTableUser();

        createDefaultUser();
    }

    void truncateTableUser() throws Exception {
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/auth_test?serverTimezone=Europe/Berlin",
                "root",
                "Vovabisnes123!");
        Statement statement = connection.createStatement();
        statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 0");
        statement.executeUpdate("TRUNCATE TABLE user");
        statement.executeUpdate("SET FOREIGN_KEY_CHECKS = 1");
        statement.close();
        connection.close();
    }

    void createDefaultUser() {
        User newUser = User.builder()
                .login(LOGIN)
                .password(PASSWORD)
                .build();

        userDAO.insert(newUser);
    }

    @Test
    void shouldFindUserById() {

        User user = userDAO.findById(1);

        assertAll(
                () -> assertEquals(user.getLogin(), LOGIN, "Incorrect Login"),
                () -> assertEquals(user.getPassword(), PASSWORD, "Incorrect Password")
        );
    }

    @Test
    void shouldUpdateUser() {

        User user = userDAO.findById(1);

        user.setLogin("Ben");
        user.setPassword("1234");

        userDAO.update(user);

        User updatedUser = userDAO.findById(1);

        assertAll(
                () -> assertEquals(updatedUser.getLogin(), "Ben", "Incorrect Login"),
                () -> assertEquals(updatedUser.getPassword(), "1234", "Incorrect Password")
        );
    }

    @Test
    void shouldDeleteUser() {
        userDAO.deleteById(1);
        assertEquals(0, userDAO.countUserByLogin(LOGIN));
    }

    @Test
    void shouldFindAllUsers() {
        User newUser1 = User.builder()
                .login(LOGIN)
                .password(PASSWORD)
                .build();
        User newUser2 = User.builder()
                .login(LOGIN)
                .password(PASSWORD)
                .build();

        userDAO.insert(newUser1);
        userDAO.insert(newUser2);

        assertEquals(3, userDAO.countAllUsers());
    }

    @Test
    void shouldFindByLogin() {
        assertAll(() -> assertEquals(null, userDAO.findByLogin("Den")),
                () -> assertEquals(LOGIN, userDAO.findByLogin(LOGIN).getLogin()),
                () -> assertEquals(PASSWORD, userDAO.findByLogin(LOGIN).getPassword()));
    }
}