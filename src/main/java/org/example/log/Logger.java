package org.example.log;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Logger {
    org.slf4j.Logger logger;

    static String GREEN_COLOR = "\u001B[32m";
    static String DEFAULT_COLOR = "\u001B[0m";
    static String RED_COLOR = "\033[31m";

    public Logger() {
        this.logger = org.slf4j.LoggerFactory.getLogger(Logger.class);
    }

    public Logger(org.slf4j.Logger logger) {
        this.logger = logger;
    }

    public void info(String text){
        logger.info(GREEN_COLOR + text + DEFAULT_COLOR);
    }

    public void error(String text){
        logger.info(RED_COLOR + text + DEFAULT_COLOR);
    }

    public void logHeaders(Map<String, String> requestHeaders) {
        for (String key : requestHeaders.keySet()) {
            String value = requestHeaders.get(key);
            info(String.format("Header: %s = %s", key, value));
        }
    }

    public void logParameters(Map<String, String[]> params) {
        for (String key : params.keySet()) {
            String value = params.get(key)[0];
            info(String.format("Request parameter: %s = %s", key, value));
        }
    }

    public void logRequestBody(String body) {
        if (body.equals("")){
            info("Request body: No Body");
        } else {
            info(String.format("Request body: %s", body));
        }
    }
}
