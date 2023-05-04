package org.example.servlets;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.authorization.Authentication;
import org.example.authorization.service.UserServiceImpl;
import org.example.log.Logger;
import org.example.request.RequestHandler;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/main")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MainServlet extends HttpServlet {
    RequestHandler requestHandler = new RequestHandler();
    Authentication authentication = new Authentication();
    Logger logger = new Logger();
    UserServiceImpl userService = new UserServiceImpl();
    static String DEFAULT_COLOR = "\u001B[0m";
    static String RED_COLOR = "\033[31m";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        processRequest(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        processRequest(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) {
        try {
            if (isUserAuthenticated(req, userService)) {
                requestHandler.makeResponseIfAuthorized(req, resp, logger);
            } else {
                requestHandler.makeResponseIfUnauthorized(resp, logger);
            }
        } catch (IOException e) {
            logger.log(RED_COLOR + "IOException occurred" + DEFAULT_COLOR);
        }
    }

    private boolean isUserAuthenticated(HttpServletRequest req, UserServiceImpl userService) {
        return authentication.isAuthenticated(req, userService);
    }
}
