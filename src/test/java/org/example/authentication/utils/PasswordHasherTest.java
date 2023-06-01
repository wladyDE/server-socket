package org.example.authentication.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordHasherTest {
    @Test
    public void testHashPassword() {
        String password = "password123";
        String login = "testuser";
        String hashedPassword = PasswordHasher.hashPassword(password, login);
        assertNotNull(hashedPassword);
        assertNotEquals(password, hashedPassword);
    }

    @Test
    public void testVerifyPassword() {
        String password = "password123";
        String hashedPassword = PasswordHasher.hashPassword(password, "user");
        boolean isPasswordVerified = PasswordHasher.verifyPassword(
                "RHjBIlLznEp6I9xJDzUEDg==", hashedPassword);
        assertTrue(isPasswordVerified);
    }
}