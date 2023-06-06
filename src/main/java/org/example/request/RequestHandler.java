package org.example.request;

import org.example.authentication.Authentication;
import org.example.authorization.Authorization;
import org.example.domain.UserDTO;
import org.example.exception.NoAccessException;
import org.example.exception.NoAuthenticationException;
import org.example.exception.NotExistingUserException;
import org.example.logger.Logger;
import org.example.request.data.*;
import org.example.request.method.Method;
import org.example.request.method.Request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestHandler {
    private final Authentication authentication;
    private final Authorization authorization;
    private RequestData requestData;
    private UserDTO currentUser;

    public RequestHandler() {
        this.authentication = new Authentication();
        this.authorization = new Authorization();
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            processAuthentication(request);

            Logger.logAuthenticationInfo(currentUser.getLogin());

            handleRequest(request, response);

        } catch (NoAuthenticationException | NotExistingUserException | NoAccessException exception){
            Logger.error(exception.toString());
            makeUnauthenticatedResponse(response, exception);
        }
    }

    private void processAuthentication(HttpServletRequest request) throws NoAuthenticationException, NotExistingUserException, NoAccessException {
        authentication.validateAuthorizationHeader(request);
        currentUser = authentication.findUser(request);
        authorization.verifyAccessRights(request, currentUser);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Method requestMethod = Method.valueOf(request.getMethod());
        Request REQUEST = RequestFactory.getRequest(requestMethod);
        REQUEST.processRequest(request, response);
    }

    private void getRequestData(HttpServletRequest request){
        Parameters parameters = new Parameters(request);
        RequestBody body = new RequestBody(request);
        RequestHeaders headers = new RequestHeaders(request);
        requestData = new RequestData(parameters.getParameters(), body.getBody(), headers.getHeaders());
    }

    private void logRequestData(HttpServletRequest request) {
        getRequestData(request);
        Logger.logAuthenticationInfo(currentUser.getLogin());
        Logger.logRequest(requestData.getParameters(), requestData.getBody(), requestData.getHeaders());
    }

    private void makeUnauthenticatedResponse(HttpServletResponse response, Exception exception) throws IOException {
        response.setStatus(RequestStatus.getStatus(exception));
        response.getWriter().write(exception.toString());
    }
}
