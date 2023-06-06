package org.example.request;

import org.example.request.method.*;

import java.util.HashMap;
import java.util.Map;

import static org.example.request.method.Method.*;
import static org.example.request.method.Method.PUT;

public class RequestFactory {
    private static final Map<Method, Request> REQUEST_MAP = new HashMap<>();

    static {
        REQUEST_MAP.put(GET, new GetRequest());
        REQUEST_MAP.put(POST, new PostRequest());
        REQUEST_MAP.put(DELETE, new DeleteRequest());
        REQUEST_MAP.put(PUT, new PutRequest());
    }

    public static Request getRequest(Method method) {
        return REQUEST_MAP.getOrDefault(method, new GetRequest());
    }
}