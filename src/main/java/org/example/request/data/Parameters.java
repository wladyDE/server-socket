package org.example.request.data;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class Parameters implements Parseable<Map<String, String[]>>{

    @Override
    public Map<String, String[]> parse(HttpServletRequest req) {
        return req.getParameterMap();
    }
}
