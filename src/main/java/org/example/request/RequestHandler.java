package org.example.request;

import org.example.log.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RequestHandler {
    private static final String MESSAGE = "Hello World";

    public void makeRequest(HttpServletRequest req, HttpServletResponse resp, Logger logger) throws IOException {
            String method = req.getMethod();
            String path = req.getRequestURI();

            logger.log(String.format("Received %s request for %s", method, path));
            logger.log(String.format("Sending HTTP response: %s", MESSAGE));

            setResponseHeaders(resp);

            PrintWriter writer = resp.getWriter();

            logger.logParameters(req);
            logger.logBody(req);
            logger.logHeaders(req);

            writer.print(MESSAGE);
            writer.flush();
            writer.close();

            logger.log("Response sent");
    }

    private void setResponseHeaders(HttpServletResponse resp) {
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/plain");
        resp.setContentLength(MESSAGE.length());
    }
}
