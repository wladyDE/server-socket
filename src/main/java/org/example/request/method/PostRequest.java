package org.example.request.method;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.domain.User;
import org.example.service.impl.UserService;
import org.example.authentication.utils.PasswordHasher;
import org.example.logger.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class PostRequest implements Request {
    private final UserService SERVICE;
    private final ObjectMapper OBJECT_MAPPER;

    public PostRequest(){
        this.SERVICE = new UserService();
        this.OBJECT_MAPPER = new ObjectMapper();
    }

    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();
        try {
            saveUserFromJSON(reader);
        } catch (Exception e) {
            Logger.error("Post Request failed: no User!");
        }
        makeResponse(response);
    }

    private void saveUserFromJSON(BufferedReader reader) throws IOException {
        User user = OBJECT_MAPPER.readValue(reader, User.class);
        user.setPassword(PasswordHasher.hashPassword(user.getPassword(), user.getLogin()));
        SERVICE.save(user);
    }

    private void makeResponse(HttpServletResponse response) {
        List<User> users = SERVICE.findAll();

        users.forEach(user -> {
            String msg = String.format("%s %s %s\n", user.getId(), user.getLogin(), user.getPassword());
            Logger.error(msg);
            try {
                response.getWriter().write(msg);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
