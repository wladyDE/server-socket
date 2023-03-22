package org.example;

import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;


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

            logParameters(req);
            logBody(req);
            logHeaders(req);

            writer.print(MESSAGE);
            writer.flush();
            writer.close();

            LOGGER.info("Response sent");
        } catch (IOException e) {
            LOGGER.error(String.format("Error processing request: %s", e.getMessage()));
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing request");
        }
    }

    private void logHeaders(HttpServletRequest req) throws IOException {
        Enumeration<String> headerNames = req.getHeaderNames();
        LOGGER.info("Requests headers:");
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = req.getHeader(headerName);
            LOGGER.info(String.format("%s : %s", headerName, headerValue));
        }
    }

    private void logParameters(HttpServletRequest req) throws IOException {
        Map<String, String[]> params = req.getParameterMap();
        for (String key : params.keySet()) {
            String value = params.get(key)[0];
            LOGGER.info(String.format("Request parameter: %s = %s", key, value));
        }
    }

    private void logBody(HttpServletRequest req) throws IOException {
        BufferedReader reader = req.getReader();
        StringBuilder body = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            body.append(line);
        }
        reader.close();
        String requestBody = body.toString();
        LOGGER.info(String.format("Request body: %s", requestBody));
    }
}
