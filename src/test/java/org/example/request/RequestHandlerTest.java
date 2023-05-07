package org.example.request;

import org.example.log.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class RequestHandlerTest {
    private static final String METHOD = "GET";

    private static final String PATH = "/main";

    private static final String MESSAGE = "Hello World";
    private static final String GREEN_COLOR = "\u001B[32m";
    private static final String DEFAULT_COLOR = "\u001B[0m";
    private static final String RED_COLOR = "\033[31m";

    @Mock
    private HttpServletRequest req;

    @Mock
    private HttpServletResponse resp;

    @Mock
    private Logger logger;

    @Mock
    private PrintWriter writer;

    private RequestHandler requestHandler;

 /*   @BeforeEach
    void setUp() throws IOException {
        doNothing().when(writer).close();
        doNothing().when(writer).flush();
        doNothing().when(writer).print(anyString());

        doNothing().when(logger).logRequestHeaders(req);
        doNothing().when(logger).logBody(req);
        doNothing().when(logger).logParameters(req);

        when(resp.getWriter()).thenReturn(writer);

        doNothing().when(resp).setContentLength(MESSAGE.length());
        doNothing().when(resp).setContentType(anyString());
        doNothing().when(resp).setStatus(HttpServletResponse.SC_OK);

        doNothing().when(logger).info(anyString());
        when(req.getRequestURI()).thenReturn(PATH);
        when(req.getMethod()).thenReturn(METHOD);

        requestHandler = new RequestHandler();
    }

    @Test
    void makeResponseIfAuthorizedTest() throws IOException {
        requestHandler.makeResponseIfAuthorized(req, resp, logger);

        verify(logger).info(String.format(GREEN_COLOR + "Received %s request for %s" + DEFAULT_COLOR, METHOD, PATH));
        verify(logger).info(String.format(GREEN_COLOR + "Sending HTTP response: %s" + DEFAULT_COLOR, MESSAGE));
        verify(logger).logParameters(req);
        verify(logger).logBody(req);
        verify(logger).logRequestHeaders(req);
        verify(logger).info(GREEN_COLOR + "Response sent" + DEFAULT_COLOR);
    }

    @Test
    void makeResponseIfUnauthorizedTest() throws IOException {
        doNothing().when(resp).setStatus(anyInt());
        doNothing().when(writer).write(anyString());

        requestHandler.makeResponseIfUnauthorized(resp, logger);

        verify(logger).info(RED_COLOR + "The user does not exist" + DEFAULT_COLOR);
    }*/
}