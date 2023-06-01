package org.example.authentication;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.example.domain.User;
import org.example.exception.NoAuthenticationException;
import org.example.exception.NotExistingUserException;
import org.example.service.impl.UserServiceImpl;
import org.example.authentication.utils.BasicAuthDecoder;
import org.example.authentication.utils.PasswordHasher;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Authentication {
    User currentUser = null;

    public boolean hasAuthentication(HttpServletRequest req) throws NoAuthenticationException {
        String authHeader = req.getHeader("Authorization");
        if(authHeader == null || !authHeader.startsWith("Basic ")){
            throw new NoAuthenticationException();
        }
        return true;
    }

    public boolean isUserAuthenticated(HttpServletRequest req, UserServiceImpl userService) throws NotExistingUserException {
        String authHeader = req.getHeader("Authorization");
        String userCredentials = BasicAuthDecoder.decodeBasicAuth(authHeader);

        String[] userData = userCredentials.split(":");
        if (userData.length != 2) {
            return false;
        }

        String login = userData[0];
        String password = PasswordHasher.hashPassword(userData[1], login);

        return authenticate(login, password, userService);
    }

    private boolean authenticate(String login, String password, UserServiceImpl userService) throws NotExistingUserException {
        currentUser = userService.findByLogin(login);
        if (!Objects.nonNull(currentUser) || !PasswordHasher.verifyPassword(password, currentUser.getPassword())) {
            throw new NotExistingUserException();
        }
        return true;
    }
}
