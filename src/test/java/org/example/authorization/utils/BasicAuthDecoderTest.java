package org.example.authorization.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BasicAuthDecoderTest {
    @Test
    public void testDecodeBasicAuth() {
        String authHeader = "Basic dXNlcjpwYXNzd29yZA==";
        String decoded = BasicAuthDecoder.decodeBasicAuth(authHeader);
        assertEquals("user:password", decoded);
    }
}