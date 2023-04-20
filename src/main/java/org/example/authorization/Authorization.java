package org.example.authorization;

import org.example.authorization.domain.User;
import org.example.authorization.service.UserServiceImpl;
import org.example.authorization.utils.BasicAuthDecoder;
import org.example.authorization.utils.PasswordHasher;

import javax.servlet.http.HttpServletRequest;

public class Authorization {

    public boolean isAuthorized(HttpServletRequest req, UserServiceImpl userService) {
        String authHeader = req.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            return false;
        }

        String decodedCredentials = BasicAuthDecoder.decodeBasicAuth(authHeader);

        String[] credentials = decodedCredentials.split(":");
        if (credentials.length != 2) {
            return false;
        }

        String login = credentials[0];
        String password = PasswordHasher.hashPassword(credentials[1], login);

        return authenticate(login, password, userService);
    }

    private boolean authenticate(String login, String password, UserServiceImpl userService) {
        User user = userService.findByLogin(login);
        if (user == null) {
            return false;
        } else {
            return PasswordHasher.verifyPassword(password, user.getPassword());
        }
    }
}
