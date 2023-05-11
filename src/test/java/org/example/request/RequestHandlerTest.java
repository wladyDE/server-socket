package org.example.request;

import org.example.authorization.Authentication;
import org.example.authorization.domain.User;
import org.example.authorization.service.UserServiceImpl;
import org.example.log.Logger;
import org.example.response.ResponseHeaders;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class RequestHandlerTest {
    private static final String METHOD = "GET";

    private static final String PATH = "/main";

    private static final String MESSAGE = "Hello World";

    @Mock
    private HttpServletRequest req;

    @Mock
    private HttpServletResponse resp;

    @Mock
    private Logger logger;

    @Mock
    private Authentication authentication;

    @Mock
    private UserServiceImpl userService;

    @Mock
    private PrintWriter writer;

    private RequestHandler requestHandler;

    @BeforeEach
    void setUp() {
        requestHandler = new RequestHandler(authentication, userService);
    }

    @Test
    void processRequestTest_WhenUserAuthenticated() throws IOException {
        try (MockedStatic<Parameters> parametersMock = mockStatic(Parameters.class);
             MockedStatic<RequestBody> requestBodyMock = mockStatic(RequestBody.class);
             MockedStatic<RequestHeaders> requestHeadersMock = mockStatic(RequestHeaders.class);
             MockedStatic<ResponseHeaders> responseHeadersMock = mockStatic(ResponseHeaders.class)) {

            parametersMock.when(() -> Parameters.parseParameters(req)).thenReturn(new HashMap<String, String[]>());
            requestBodyMock.when(() -> RequestBody.parseBody(req)).thenReturn("Body");
            requestHeadersMock.when(() -> RequestHeaders.parseRequestHeaders(req)).thenReturn(new HashMap<String, String>());

            responseHeadersMock.when(() -> ResponseHeaders.setResponseHeaders(resp, MESSAGE))
                    .thenCallRealMethod();
            requestBodyMock.when(() -> RequestBody.sendBody(resp, MESSAGE))
                    .thenCallRealMethod();

            when(resp.getWriter()).thenReturn(writer);

            when(authentication.getUser())
                    .thenReturn(User.builder()
                            .login("login")
                            .password("password")
                            .build());

            when(req.getMethod()).thenReturn(METHOD);
            when(req.getRequestURI()).thenReturn(PATH);
            doNothing().when(logger).info(anyString());
            doNothing().when(logger).logParameters(anyMap());
            doNothing().when(logger).logRequestBody(anyString());
            doNothing().when(logger).logHeaders(anyMap());

            when(authentication.isAuthenticated(req, userService)).thenReturn(true);

            requestHandler.processRequest(req, resp, logger);

            verify(writer).print(MESSAGE);
        }
    }

    @Test
    void processRequestTest_WhenUserNotAuthenticated() throws IOException {
        try (MockedStatic<Parameters> parametersMock = mockStatic(Parameters.class);
             MockedStatic<RequestBody> requestBodyMock = mockStatic(RequestBody.class);
             MockedStatic<RequestHeaders> requestHeadersMock = mockStatic(RequestHeaders.class)) {

            parametersMock.when(() -> Parameters.parseParameters(req)).thenReturn(new HashMap<String, String[]>());
            requestBodyMock.when(() -> RequestBody.parseBody(req)).thenReturn("Body");
            requestHeadersMock.when(() -> RequestHeaders.parseRequestHeaders(req)).thenReturn(new HashMap<String, String>());

            when(resp.getWriter()).thenReturn(writer);

            when(req.getMethod()).thenReturn(METHOD);
            when(req.getRequestURI()).thenReturn(PATH);
            doNothing().when(logger).info(anyString());
            doNothing().when(logger).logParameters(anyMap());
            doNothing().when(logger).logRequestBody(anyString());
            doNothing().when(logger).logHeaders(anyMap());

            doNothing().when(logger).error(anyString());

            when(authentication.isAuthenticated(req, userService)).thenReturn(false);

            requestHandler.processRequest(req, resp, logger);

            verify(writer).write("Unauthorized");
            verify(resp).setStatus(401);
        }
    }

}