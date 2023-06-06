package org.example.authentication;

import org.example.authentication.utils.BasicAuthDecoder;
import org.example.authentication.utils.PasswordHasher;
import org.example.domain.User;
import org.example.domain.UserDTO;
import org.example.exception.NoAuthenticationException;
import org.example.exception.NotExistingUserException;
import org.example.service.IUserService;
import org.example.service.impl.UserService;

import javax.servlet.http.HttpServletRequest;

public class Authentication {
    private final static String AUTHORIZATION = "Authorization";
    private final static String BASIC = "Basic ";
    private final IUserService SERVICE;

    public Authentication(){
        this.SERVICE = new UserService();
    }

    public void validateAuthorizationHeader(HttpServletRequest request) throws NoAuthenticationException {
        String authHeader = request.getHeader(AUTHORIZATION);
        if(authHeader == null || !authHeader.startsWith(BASIC)){
            throw new NoAuthenticationException();
        }
    }

    public UserDTO findUser(HttpServletRequest request) throws NotExistingUserException {
        String authHeader = request.getHeader(AUTHORIZATION);
        String userCredentials = BasicAuthDecoder.decodeBasicAuth(authHeader);

        String[] userData = userCredentials.split(":");
        if (userData.length != 2) {
            throw new NotExistingUserException();
        }

        String login = userData[0];
        String password = PasswordHasher.hashPassword(userData[1], login);

        User currentUser = SERVICE.findByLogin(login);
        if (currentUser == null || !PasswordHasher.verifyPassword(password, currentUser.getPassword())) {
            throw new NotExistingUserException();
        }
        return UserDTO.builder()
                .login(currentUser.getLogin())
                .roles(currentUser.getRoles())
                .build();
    }
}
