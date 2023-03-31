package org.example.log;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Map;

public class Logger {
    private final org.slf4j.Logger logger;

    public Logger() {
        this.logger = org.slf4j.LoggerFactory.getLogger(Logger.class);
    }

    public void log(String info){
        logger.info(info);
    }

    public void logHeaders(HttpServletRequest req) {
        Enumeration<String> headerNames = req.getHeaderNames();
        log("Requests headers:");
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
            log(String.format("Request parameter: %s = %s", key, value));
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
        log(String.format("Request body: %s", requestBody));
    }
}
