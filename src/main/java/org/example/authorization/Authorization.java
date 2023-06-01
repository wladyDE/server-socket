package org.example.authorization;

import org.example.domain.RoleName;
import org.example.domain.User;
import org.example.exception.NoAccessException;
import org.example.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;

public class Authorization {
    private UserServiceImpl service = new UserServiceImpl();

    public boolean isValidAuthorization (HttpServletRequest req, User user) throws NoAccessException {
        if (req.getMethod().equals("GET") || req.getMethod().equals("POST")){
            return true;
        }
        return hasAdminRole(user);
    }

    private boolean hasAdminRole(User user) throws NoAccessException {
        if (user.getRoles().stream()
                .anyMatch(role -> role.getRoleName().equals(RoleName.ROLE_ADMIN))) {
            return true;
        } else throw new NoAccessException();
    }
}
