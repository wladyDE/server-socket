package org.example.response;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class ResponseHeaders {
    public static void setResponseHeaders(HttpServletResponse resp, String message) {
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/plain");
        resp.setContentLength(message.length());
    }

    public static Map<String, String> parseHeaders(HttpServletResponse resp){
        Map<String, String> responseHeaders = new HashMap<>();

        for (String headerName : resp.getHeaderNames()) {
            String headerValue = resp.getHeader(headerName);
            responseHeaders.put(headerName, headerValue);
        }
        return responseHeaders;
    }
}
