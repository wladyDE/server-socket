package org.example.log;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class LoggerTest {
    @Mock
    private HttpServletRequest req;

    @Mock
    private org.slf4j.Logger loggerMock;

    private Logger loggerClass;

    @BeforeEach
    void setUp(){
        loggerClass = new Logger(loggerMock);
    }

    @Test
    void logTest(){
        loggerClass.log("info");
        verify(loggerMock).info("info");
    }

    @Test
    void logHeadersTest() {
        when(req.getHeaderNames()).thenReturn(new Vector<>(Arrays.asList("header1", "header2")).elements());
        when(req.getHeader("header1")).thenReturn("value1");
        when(req.getHeader("header2")).thenReturn("value2");

        loggerClass.logHeaders(req);

        verify(loggerMock).info("Requests headers:");
        verify(loggerMock).info("header1 : value1");
        verify(loggerMock).info("header2 : value2");
    }

    @Test
    void logParameters(){
        Map<String, String[]> parameterMap = new HashMap<>();
        parameterMap.put("param1", new String[]{"value1"});
        parameterMap.put("param2", new String[]{"value2"});
        when(req.getParameterMap()).thenReturn(parameterMap);

        loggerClass.logParameters(req);

        verify(loggerMock).info("Request parameter: param1 = value1");
        verify(loggerMock).info("Request parameter: param2 = value2");
    }

    @Test
    void logBody() throws IOException {
        String requestBody = "request body";
        BufferedReader reader = new BufferedReader(new StringReader(requestBody));
        when(req.getReader()).thenReturn(reader);

        loggerClass.logBody(req);

        verify(loggerMock).info(String.format("Request body: %s", requestBody));
    }
}