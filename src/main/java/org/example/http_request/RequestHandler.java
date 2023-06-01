package org.example.http_request;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.authentication.Authentication;
import org.example.exception.NoAccessException;
import org.example.exception.NoAuthenticationException;
import org.example.exception.NotExistingUserException;
import org.example.authorization.Authorization;
import org.example.domain.User;
import org.example.http_request.request_data.Parameters;
import org.example.http_request.request_data.RequestBody;
import org.example.http_request.request_data.RequestHeaders;
import org.example.service.impl.UserServiceImpl;
import org.example.logger.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RequestHandler {
    Authentication authentication = new Authentication();
    Authorization authorization = new Authorization();
    UserServiceImpl userService = new UserServiceImpl();
    Requests requests = new Requests();

    public void processRequest(HttpServletRequest req, HttpServletResponse resp, Logger logger) throws IOException {
        try {
            if (hasAuthentication(req)
                    && isUserAuthenticated(req, userService)
                    && isValidAuthorization(req, authentication.getCurrentUser())){

                //logRequestData(req, logger);
                logAuthenticationData(logger);

                String method = req.getMethod();
                switch (method) {
                    case "GET" -> requests.getGetRequest().processRequest(req, resp, logger);
                    case "POST" -> requests.getPostRequest().processRequest(req, resp, logger);
                    case "PUT" -> requests.getPutRequest().processRequest(req, resp, logger);
                    case "DELETE" -> requests.getDeleteRequest().processRequest(req, resp, logger);
                }
            }
        } catch (NoAuthenticationException | NotExistingUserException | NoAccessException e){
            logger.error(e.toString());
            makeUnauthenticatedResponse(resp, e);
        }
    }

    private void logRequestData(HttpServletRequest req, Logger logger) throws IOException {
        Parameters p = new Parameters();
        RequestBody b = new RequestBody();
        RequestHeaders h = new RequestHeaders();

        Map<String, String[]> parameters = p.parse(req);
        String body = b.parse(req);
        Map<String, String> headers = h.parse(req);

        logAuthenticationData(logger);
        logger.logRequest(parameters, body, headers);
    }

    private void logAuthenticationData(Logger logger){
        User currentUser = authentication.getCurrentUser();
        logger.logAuthenticationInfo(currentUser.getLogin(), currentUser.getPassword());
    }

    private void makeUnauthenticatedResponse(HttpServletResponse resp, Exception e) throws IOException {
        resp.setStatus(getStatus(e));
        resp.getWriter().write(e.toString());
    }

    private boolean isUserAuthenticated(HttpServletRequest req, UserServiceImpl userService) throws NotExistingUserException {
        return authentication.isUserAuthenticated(req, userService);
    }

    private boolean hasAuthentication(HttpServletRequest req) throws NoAuthenticationException {
        return authentication.hasAuthentication(req);
    }

    private boolean isValidAuthorization(HttpServletRequest req, User user) throws NoAccessException {
        return authorization.isValidAuthorization(req, user);
    }

    private int getStatus(Exception e){
        if (e instanceof NoAuthenticationException) {
            return 401;
        } else if (e instanceof NotExistingUserException) {
            return 404;
        } else if (e instanceof NoAccessException) {
            return 403;
        } else {
            return 500;
        }
    }
}
