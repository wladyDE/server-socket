package org.example.request;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.log.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RequestHandler {
    static String MESSAGE = "Hello World";
    static String GREEN_COLOR = "\u001B[32m";
    static String DEFAULT_COLOR = "\u001B[0m";
    static String RED_COLOR = "\033[31m";

    public void makeResponseIfAuthorized(HttpServletRequest req, HttpServletResponse resp, Logger logger) throws IOException {
            String method = req.getMethod();
            String path = req.getRequestURI();

            logger.log(String.format(GREEN_COLOR + "Received %s request for %s" + DEFAULT_COLOR, method, path));
            logger.log(String.format(GREEN_COLOR + "Sending HTTP response: %s" + DEFAULT_COLOR, MESSAGE));

            setResponseHeaders(resp);

            PrintWriter writer = resp.getWriter();

            logger.logParameters(req);
            logger.logBody(req);
            logger.logHeaders(req);

            writer.print(MESSAGE);
            writer.flush();
            writer.close();

            logger.log(GREEN_COLOR + "Response sent" + DEFAULT_COLOR);
    }

    public void makeResponseIfUnauthorized(HttpServletResponse resp, Logger logger) throws IOException {
        resp.setStatus(401);
        resp.getWriter().write("Unauthorized");
        logger.log(RED_COLOR + "The user does not exist" + DEFAULT_COLOR);
    }

    private void setResponseHeaders(HttpServletResponse resp) {
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.setContentType("text/plain");
        resp.setContentLength(MESSAGE.length());
    }
}
