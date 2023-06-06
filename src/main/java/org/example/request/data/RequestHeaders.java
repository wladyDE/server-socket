package org.example.request.data;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class RequestHeaders implements Parseable<Map<String, String>> {
    private final Map<String, String> headers;

    public RequestHeaders(HttpServletRequest request) {
        this.headers = parse(request);
    }

    public Map<String, String> parse(HttpServletRequest req) {
        Map<String, String> requestHeaders = new HashMap<>();
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = req.getHeader(headerName);
            requestHeaders.put(headerName, headerValue);
        }
        return requestHeaders;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
}
