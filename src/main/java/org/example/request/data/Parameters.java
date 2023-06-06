package org.example.request.data;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class Parameters implements Parseable<Map<String, String[]>>{
    private final Map<String, String[]> parameters;

    public Parameters(HttpServletRequest request) {
        this.parameters = parse(request);
    }

    @Override
    public Map<String, String[]> parse(HttpServletRequest request) {
        return request.getParameterMap();
    }

    public Map<String, String[]> getParameters() {
        return parameters;
    }
}
