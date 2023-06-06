package org.example.request.method;

import org.example.domain.User;
import org.example.service.impl.UserService;
import org.example.logger.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteRequest implements Request{
    private final UserService SERVICE;

    public DeleteRequest(){
        this.SERVICE = new UserService();
    }

    @Override
    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        User deletedUser = SERVICE.findById(id);

        if (deletedUser != null){
            SERVICE.deleteById(id);
            makeResponse(response, deletedUser);
        } else {
            makeResponse(response);
        }
    }

    private void makeResponse(HttpServletResponse response, User deletedUser) throws IOException {
        String msg = String.format("The user %s was deleted", deletedUser.getLogin());
        Logger.error(msg);
        response.getWriter().write(msg);
    }

    private void makeResponse(HttpServletResponse response) throws IOException {
        String msg = "There is no User with such id";
        Logger.error(msg);
        response.getWriter().write(msg);
        response.setStatus(404);
    }
}
