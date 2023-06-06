package org.example.request.data;

import org.example.exception.NoAccessException;
import org.example.exception.NoAuthenticationException;
import org.example.exception.NotExistingUserException;

public class RequestStatus {
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int INTERNAL_SERVER_ERROR = 500;

    public static int getStatus(Exception exception){
        if (exception instanceof NoAuthenticationException) {
            return UNAUTHORIZED;
        } else if (exception instanceof NotExistingUserException) {
            return NOT_FOUND;
        } else if (exception instanceof NoAccessException) {
            return FORBIDDEN;
        } else {
            return INTERNAL_SERVER_ERROR;
        }
    }
}
