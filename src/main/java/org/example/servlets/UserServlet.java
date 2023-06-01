package org.example.servlets;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.http_request.RequestHandler;
import org.example.logger.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/user")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServlet extends HttpServlet {
    RequestHandler REQUEST = new RequestHandler();
    Logger LOGGER = new Logger();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        REQUEST.processRequest(req, resp, LOGGER);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        REQUEST.processRequest(req, resp, LOGGER);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        REQUEST.processRequest(req, resp, LOGGER);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        REQUEST.processRequest(req, resp, LOGGER);
    }
}
