package org.example.log;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class LoggerTest {

    @Mock
    private org.slf4j.Logger loggerMock;

    private Logger loggerClass;

    static String GREEN_COLOR = "\u001B[32m";
    static String DEFAULT_COLOR = "\u001B[0m";
    static String RED_COLOR = "\033[31m";

    @BeforeEach
    void setUp(){
        loggerClass = new Logger(loggerMock);
    }

    @Test
    void logTest(){
        loggerClass.info("info");
        verify(loggerMock).info(GREEN_COLOR + "info" + DEFAULT_COLOR);
    }

    @Test
    void logErrorTest(){
        loggerClass.error("info");
        verify(loggerMock).info(RED_COLOR + "info" + DEFAULT_COLOR);
    }

    @Test
    void logHeadersTest() {
        Map<String, String> parameterMap = new HashMap<>();
        parameterMap.put("key", "value");

        loggerClass.logHeaders(parameterMap);

        verify(loggerMock).info(GREEN_COLOR + "Header: key = value" + DEFAULT_COLOR);
    }

    @Test
    void logParametersTest() {
        Map<String, String[]> parameterMap = new HashMap<>();
        parameterMap.put("key", new String[]{"value"});

        loggerClass.logParameters(parameterMap);

        verify(loggerMock).info(GREEN_COLOR + "Request parameter: key = value" + DEFAULT_COLOR);
    }

    @Test
    void logBodyTest_WhenNoBody(){
        loggerClass.logRequestBody("");
        verify(loggerMock).info(GREEN_COLOR + "Request body: No Body" + DEFAULT_COLOR);
    }

    @Test
    void logBodyTest_WhenBodyExists(){
        loggerClass.logRequestBody("body");
        verify(loggerMock).info(GREEN_COLOR + "Request body: body" + DEFAULT_COLOR);
    }
}