package org.example.http_request.method;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.domain.User;
import org.example.service.impl.UserServiceImpl;
import org.example.logger.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class GetRequest implements Request {
    UserServiceImpl SERVICE = new UserServiceImpl();
    ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public void processRequest(HttpServletRequest req, HttpServletResponse resp, Logger logger) {
        List<User> users = SERVICE.findAll();

        List<String> json = users.stream()
                .map(user -> {
                    try {
                        return OBJECT_MAPPER.writeValueAsString(user);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                        return "";
                    }
                })
                .collect(Collectors.toList());

        makeGetResponse(resp, json, logger);
    }

    private void makeGetResponse(HttpServletResponse resp, List<String> users, Logger logger) {
        users.forEach(user -> {
            logger.error(user);
            try {
                resp.getWriter().write(user);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
