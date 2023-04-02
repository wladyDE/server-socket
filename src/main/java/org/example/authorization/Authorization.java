package org.example.authorization;

import org.example.authorization.service.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

public class Authorization {

    public boolean isAuthorized(HttpServletRequest req, UserServiceImpl userService) {
        String authHeader = req.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            return false;
        }

        String encodedCredentials = authHeader.substring("Basic ".length()).trim();
        String decodedCredentials = new String(Base64.getDecoder().decode(encodedCredentials));

        String[] credentials = decodedCredentials.split(":");
        if (credentials.length != 2) {
            return false;
        }

        String username = credentials[0];
        String password = credentials[1];

        return userService.countUsersByNameAndPassword(username, password) == 1;
    }
}
