package org.example.request.method;

import org.example.domain.User;
import org.example.service.impl.UserServiceImpl;
import org.example.logger.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteRequest implements Request{
    UserServiceImpl SERVICE = new UserServiceImpl();

    @Override
    public void processRequest(HttpServletRequest req, HttpServletResponse resp, Logger logger) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        User deletedUser = SERVICE.findById(id);

        if (deletedUser != null){
            SERVICE.deleteById(id);
            makeResponse(resp, logger, deletedUser);
        } else {
            makeResponse(resp, logger);
        }
    }

    private void makeResponse(HttpServletResponse resp, Logger logger, User deletedUser) throws IOException {
        String msg = String.format("The user %s was deleted", deletedUser.getLogin());
        logger.error(msg);
        resp.getWriter().write(msg);
    }

    private void makeResponse(HttpServletResponse resp, Logger logger) throws IOException {
        String msg = "There is no User with such id";
        logger.error(msg);
        resp.getWriter().write(msg);
        resp.setStatus(404);
    }
}
