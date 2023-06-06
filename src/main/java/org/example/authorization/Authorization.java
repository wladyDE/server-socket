package org.example.authorization;

import org.example.domain.RoleName;
import org.example.domain.UserDTO;
import org.example.exception.NoAccessException;
import org.example.request.method.Method;

import javax.servlet.http.HttpServletRequest;

import static org.example.request.method.Method.GET;
import static org.example.request.method.Method.POST;

public class Authorization {

    public void verifyAccessRights(HttpServletRequest request, UserDTO user) throws NoAccessException {
        Method requestMethod = Method.valueOf(request.getMethod());
        if (!requestMethod.equals(GET) && !requestMethod.equals(POST)){
            if(!hasAdminRole(user)){
                throw new NoAccessException();
            }
        }
    }

    private boolean hasAdminRole(UserDTO user) {
        return user.getRoles().stream()
                .anyMatch(role -> role.getRoleName().equals(RoleName.ROLE_ADMIN));
    }
}
