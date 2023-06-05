package org.example.logger;

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

    public void info(String msg, Object... args){
        String text = String.format(msg, args);
        logger.info(GREEN_COLOR + text + DEFAULT_COLOR);
    }

    public void error(String msg, Object... args){
        String text = String.format(msg, args);
        logger.info(RED_COLOR + text + DEFAULT_COLOR);
    }


    public void logRequest(Map<String, String[]> parameters, String body, Map<String, String> headers) {
        logParameters(parameters);
        logRequestBody(body);
        info("Request Headers:");
        logHeaders(headers);
    }

    public void logHeaders(Map<String, String> requestHeaders) {
        for (String key : requestHeaders.keySet()) {
            String value = requestHeaders.get(key);
            info("Header: %s = %s", key, value);
        }
    }

    private void logParameters(Map<String, String[]> params) {
        for (String key : params.keySet()) {
            String value = params.get(key)[0];
            info("Request parameter: %s = %s", key, value);
        }
    }

    private void logRequestBody(String body) {
        if (body.equals("")){
            info("Request body: No Body");
        } else {
            info("Request body: %s", body);
        }
    }

    public void logAuthenticationInfo(String login, String password) {
        logger.error("Authentication found!");
        logger.error("Login: %s, Password: %s", login, password);
    }
}
