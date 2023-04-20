package org.example.servlets;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.example.authorization.domain.User;
import org.example.authorization.service.UserServiceImpl;
import org.example.authorization.utils.PasswordHasher;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/user")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServlet extends HttpServlet {
    static UserServiceImpl SERVICE = new UserServiceImpl();
    static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
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

        writer.println(json);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader reader = req.getReader();

        User user = OBJECT_MAPPER.readValue(reader, User.class);

        user.setPassword(PasswordHasher.hashPassword(user.getPassword(), user.getLogin()));

        SERVICE.save(user);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedReader reader = req.getReader();

        User user = OBJECT_MAPPER.readValue(reader, User.class);

        SERVICE.update(user);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));

        SERVICE.deleteById(id);
    }
}
