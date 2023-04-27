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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (!authentication.isAuthenticated(req, userService)) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().write("Unauthorized");
            return;
        }

        requestHandler.makeRequest(req, resp, logger);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (!authentication.isAuthenticated(req, userService)) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().write("Unauthorized");
            return;
        }

        requestHandler.makeRequest(req, resp, logger);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (!authentication.isAuthenticated(req, userService)) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().write("Unauthorized");
            return;
        }

        requestHandler.makeRequest(req, resp, logger);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (!authentication.isAuthenticated(req, userService)) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().write("Unauthorized");
            return;
        }

        requestHandler.makeRequest(req, resp, logger);
    }
}
