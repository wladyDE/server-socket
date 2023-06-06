package org.example.logger;

import java.util.Map;

public final class Logger {
    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(Logger.class);

    private final static String GREEN_COLOR = "\u001B[32m";
    private final static String DEFAULT_COLOR = "\u001B[0m";
    private final static String RED_COLOR = "\033[31m";

    public static void info(String msg, Object... args){
        String text = String.format(msg, args);
        logger.info(GREEN_COLOR + text + DEFAULT_COLOR);
    }

    public static void error(String msg, Object... args){
        String text = String.format(msg, args);
        logger.info(RED_COLOR + text + DEFAULT_COLOR);
    }


    public static void logRequest(Map<String, String[]> parameters, String body, Map<String, String> headers) {
        logParameters(parameters);
        logRequestBody(body);
        info("Request Headers:");
        logHeaders(headers);
    }

    public static void logHeaders(Map<String, String> requestHeaders) {
        for (String key : requestHeaders.keySet()) {
            String value = requestHeaders.get(key);
            info("Header: %s = %s", key, value);
        }
    }

    private static void logParameters(Map<String, String[]> params) {
        for (String key : params.keySet()) {
            String value = params.get(key)[0];
            info("Request parameter: %s = %s", key, value);
        }
    }

    private static void logRequestBody(String body) {
        if (body.equals("")){
            info("Request body: No Body");
        } else {
            info("Request body: %s", body);
        }
    }

    public static void logAuthenticationInfo(String login) {
        info("Authentication found!");
        info("User %s was logged in", login);
    }
}
