package org.example.request;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.authorization.Authentication;
import org.example.authorization.service.UserServiceImpl;
import org.example.log.Logger;
import org.example.response.ResponseHeaders;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class RequestHandler {
    Authentication authentication = new Authentication();

    UserServiceImpl userService = new UserServiceImpl();

    public RequestHandler() {
    }

    public RequestHandler(Authentication authentication, UserServiceImpl userService){
        this.authentication = authentication;
        this.userService = userService;
    }

    static String MESSAGE = "Hello World";

    public void processRequest(HttpServletRequest req, HttpServletResponse resp, Logger logger) throws IOException {
        logRequest(req, logger);

        if (isUserAuthenticated(req, userService)) {
            makeAuthenticatedResponse(resp, logger);
        } else {
            makeUnauthenticatedResponse(resp, logger);
        }
    }

    private void logRequest(HttpServletRequest req, Logger logger) throws IOException {
        String method = req.getMethod();
        String path = req.getRequestURI();

        logger.info(String.format("Received %s request for %s", method, path));
        logger.logParameters(Parameters.parseParameters(req));
        logger.logRequestBody(RequestBody.parseBody(req));
        logger.info("Request Headers:");
        logger.logHeaders(RequestHeaders.parseRequestHeaders(req));
    }

    private void makeAuthenticatedResponse(HttpServletResponse resp, Logger logger) throws IOException {
        logger.info("Authentication found!");
        logger.info(String.format("Login: %s, Password: %s",
                authentication.getUser().getLogin(),
                authentication.getUser().getPassword()));

        ResponseHeaders.setResponseHeaders(resp, MESSAGE);
        RequestBody.sendBody(resp, MESSAGE);
        logger.info(String.format("Response Body: %s", MESSAGE));
        logger.info("Response Headers:");
        logger.logHeaders(ResponseHeaders.parseHeaders(resp));
    }

    private void makeUnauthenticatedResponse(HttpServletResponse resp, Logger logger) throws IOException {
        resp.setStatus(401);
        resp.getWriter().write("Unauthorized");
        logger.error("No Authentication!");
        logger.error("The user does not exist!");
    }

    private boolean isUserAuthenticated(HttpServletRequest req, UserServiceImpl userService) {
        return authentication.isAuthenticated(req, userService);
    }
}
