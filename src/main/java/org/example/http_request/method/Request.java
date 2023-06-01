package org.example.http_request.method;

import org.example.logger.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Request {
    void processRequest(HttpServletRequest req, HttpServletResponse resp, Logger logger) throws IOException;
}
