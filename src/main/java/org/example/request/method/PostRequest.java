package org.example.request.method;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.domain.User;
import org.example.service.impl.UserServiceImpl;
import org.example.authentication.utils.PasswordHasher;
import org.example.logger.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class PostRequest implements Request {
    UserServiceImpl SERVICE = new UserServiceImpl();
    ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public void processRequest(HttpServletRequest req, HttpServletResponse resp, Logger logger) throws IOException {
        BufferedReader reader = req.getReader();
        try {
            saveUserFromJSON(reader);
        } catch (Exception e) {
            logger.error("Post Request failed: no User!");
        }
        makeResponse(resp, logger);
    }

    private void saveUserFromJSON(BufferedReader reader) throws IOException {
        User user = OBJECT_MAPPER.readValue(reader, User.class);
        user.setPassword(PasswordHasher.hashPassword(user.getPassword(), user.getLogin()));
        SERVICE.save(user);
    }

    private void makeResponse(HttpServletResponse resp, Logger logger) {
        List<User> users = SERVICE.findAll();

        users.forEach(user -> {
            String msg = String.format("%s %s %s\n", user.getId(), user.getLogin(), user.getPassword());
            logger.error(msg);
            try {
                resp.getWriter().write(msg);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
