package org.example.servlets;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
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
    Logger logger = new Logger();

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
            requestHandler.processRequest(req, resp, logger);
        } catch (IOException e) {
            logger.error("IOException occurred");
            e.printStackTrace();
        }
    }
}

