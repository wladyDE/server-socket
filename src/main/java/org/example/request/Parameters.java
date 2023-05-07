package org.example.request;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class Parameters {

    public static Map<String, String[]> parseParameters(HttpServletRequest req) {
        return req.getParameterMap();
    }
}
