package org.example;

import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(urlPatterns = "/main")
public class MainServlet extends HttpServlet {
    public static final String MESSAGE = "Hello World";
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(MainServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        makeRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        makeRequest(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        makeRequest(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        makeRequest(req, resp);
    }

    private void makeRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            String method = req.getMethod();
            String path = req.getRequestURI();

            LOGGER.info(String.format("Received %s request for %s", method, path));
            LOGGER.info(String.format("Sending HTTP response: %s", MESSAGE));

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.setContentType("text/plain");
            resp.setContentLength(MESSAGE.length());

            PrintWriter writer = resp.getWriter();
            writer.print(MESSAGE);
            writer.flush();
            writer.close();

            LOGGER.info("Response sent");
        } catch (IOException e) {
            LOGGER.error(String.format("Error processing request: %s", e.getMessage()));
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing request");
        }
    }
}
