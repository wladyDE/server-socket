package org.example.log;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class Logger {
    org.slf4j.Logger logger;

    static String GREEN_COLOR = "\u001B[32m";
    static String DEFAULT_COLOR = "\u001B[0m";

    public Logger() {
        this.logger = org.slf4j.LoggerFactory.getLogger(Logger.class);
    }

    public Logger(org.slf4j.Logger logger) {
        this.logger = logger;
    }

    public void log(String info){
        logger.info(info);
    }

    public void logHeaders(HttpServletRequest req) {
        Enumeration<String> headerNames = req.getHeaderNames();
        log(GREEN_COLOR + "Requests headers:" + DEFAULT_COLOR);
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = req.getHeader(headerName);
            log(String.format("%s : %s", headerName, headerValue));
        }
    }

    public void logParameters(HttpServletRequest req) {
        Map<String, String[]> params = req.getParameterMap();
        for (String key : params.keySet()) {
            String value = params.get(key)[0];
            log(String.format(GREEN_COLOR + "Request parameter: %s = %s" + DEFAULT_COLOR, key, value));
        }
    }

    public void logBody(HttpServletRequest req) throws IOException {
        BufferedReader reader = req.getReader();
        StringBuilder body = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            body.append(line);
        }
        reader.close();
        String requestBody = body.toString();
        log(String.format(GREEN_COLOR + "Request body: %s" + DEFAULT_COLOR, requestBody));
    }
}
