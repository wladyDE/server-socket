package org.example.request.method;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.domain.User;
import org.example.service.impl.UserService;
import org.example.logger.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class PutRequest implements Request{
    private final UserService SERVICE;
    private final ObjectMapper OBJECT_MAPPER;

    public PutRequest(){
        this.SERVICE = new UserService();
        this.OBJECT_MAPPER = new ObjectMapper();
    }

    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BufferedReader reader = request.getReader();

        User userToUpdate = OBJECT_MAPPER.readValue(reader, User.class);

        SERVICE.update(userToUpdate);

        makeResponse(response, userToUpdate);
    }

    private void makeResponse(HttpServletResponse response, User userToUpdate) throws IOException {
        String msg = String.format("The user %s was updated", userToUpdate.getLogin());
        Logger.error(msg);
        response.getWriter().write(msg);
    }
}
