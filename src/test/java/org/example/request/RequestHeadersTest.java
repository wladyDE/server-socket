package org.example.request;

import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RequestHeadersTest {

    @Test
    void parseRequestHeadersTest() {
        HttpServletRequest req = mock(HttpServletRequest.class);
        Enumeration<String> headerNames = Collections.enumeration(Collections.singletonList("Content-Type"));

        when(req.getHeaderNames()).thenReturn(headerNames);
        when(req.getHeader("Content-Type")).thenReturn("application/json");

        Map<String, String> requestHeaders = RequestHeaders.parseRequestHeaders(req);

        assertEquals("application/json", requestHeaders.get("Content-Type"));
    }
}