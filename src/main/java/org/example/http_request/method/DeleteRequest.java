package org.example.http_request.method;

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

        SERVICE.deleteById(id);

        makeResponse(resp, logger, deletedUser);
    }

    private void makeResponse(HttpServletResponse resp, Logger logger, User deletedUser) throws IOException {
        String msg = String.format("The user %s was deleted", deletedUser.getLogin());
        logger.error(msg);
        resp.getWriter().write(msg);
    }
}
