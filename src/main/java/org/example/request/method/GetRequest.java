package org.example.request.method;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.domain.User;
import org.example.service.impl.UserService;
import org.example.logger.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class GetRequest implements Request {
    private final UserService SERVICE;
    private final ObjectMapper OBJECT_MAPPER;

    public GetRequest() {
        this.SERVICE = new UserService();
        this.OBJECT_MAPPER = new ObjectMapper();
    }

    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathInfo = request.getPathInfo();
        try {
            String[] pathParts = pathInfo.split("/");
            if (pathParts.length == 2 && pathParts[1].matches("\\d+")) {
                int userId = Integer.parseInt(pathParts[1]);

                findUser(userId, response);
            } else if(pathParts.length == 2 && pathParts[1].equals("all")){
                findAllUsers(response);
            }
        } catch (Exception e){
            makeResponse(response);
        }
    }

    private void findUser(int id, HttpServletResponse response) throws IOException {
        User user = SERVICE.findById(id);

        if (user != null){
            makeResponse(response, user);
        } else {
            makeResponse(response);
        }
    }

    private void findAllUsers(HttpServletResponse response) {
        List<User> users = SERVICE.findAll();

        List<String> usersJSON = users.stream()
                .map(user -> {
                    try {
                        return OBJECT_MAPPER.writeValueAsString(user);
                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                        return "";
                    }
                })
                .collect(Collectors.toList());

        makeResponse(response, usersJSON);
    }

    private void makeResponse(HttpServletResponse response, List<String> users) {
        users.forEach(user -> {
            Logger.error(user);
            try {
                response.getWriter().write(user);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        });
    }

    private void makeResponse(HttpServletResponse response, User user) throws IOException {
        String msg = String.format("User found: %s", OBJECT_MAPPER.writeValueAsString(user));
        Logger.error(msg);
        response.getWriter().write(msg);
    }

    private void makeResponse(HttpServletResponse response) throws IOException {
        String msg = "Not Found!";
        Logger.error(msg);
        response.getWriter().write(msg);
        response.setStatus(404);
    }
}
