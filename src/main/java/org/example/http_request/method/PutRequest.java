package org.example.http_request.method;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.domain.User;
import org.example.service.impl.UserServiceImpl;
import org.example.logger.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class PutRequest implements Request{
    UserServiceImpl SERVICE = new UserServiceImpl();
    ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public void processRequest(HttpServletRequest req, HttpServletResponse resp, Logger logger) throws IOException {
        BufferedReader reader = req.getReader();

        User user = OBJECT_MAPPER.readValue(reader, User.class);

        SERVICE.update(user);

        makeResponse(resp, logger, user);
    }

    private void makeResponse(HttpServletResponse resp, Logger logger, User user) throws IOException {
        String msg = String.format("The user %s was updated", user.getLogin());
        logger.error(msg);
        resp.getWriter().write(msg);
    }
}
