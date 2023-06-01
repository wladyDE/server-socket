package org.example.http_request;

import org.example.http_request.request_data.Parameters;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ParametersTest {
/*    @Test
    void parseParameters() {
        HttpServletRequest req = mock(HttpServletRequest.class);

        Map<String, String[]> parameterMap = new HashMap<>();
        parameterMap.put("param1", new String[]{"value1"});
        parameterMap.put("param2", new String[]{"value2"});
        when(req.getParameterMap()).thenReturn(parameterMap);

        Map<String, String[]> result = Parameters.parseParameters(req);

        assertArrayEquals(new String[]{"value1"}, result.get("param1"));
        assertArrayEquals(new String[]{"value2"}, result.get("param2"));
    }*/
}