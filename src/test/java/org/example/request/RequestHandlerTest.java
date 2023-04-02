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

    @Mock
    private HttpServletRequest req;

    @Mock
    private HttpServletResponse resp;

    @Mock
    private Logger logger;

    @Mock
    private PrintWriter writer;

    private RequestHandler requestHandler;

    @BeforeEach
    void setUp() throws IOException {
        doNothing().when(writer).close();
        doNothing().when(writer).flush();
        doNothing().when(writer).print(anyString());

        doNothing().when(logger).logHeaders(req);
        doNothing().when(logger).logBody(req);
        doNothing().when(logger).logParameters(req);

        when(resp.getWriter()).thenReturn(writer);

        doNothing().when(resp).setContentLength(MESSAGE.length());
        doNothing().when(resp).setContentType(anyString());
        doNothing().when(resp).setStatus(HttpServletResponse.SC_OK);

        doNothing().when(logger).log(anyString());
        when(req.getRequestURI()).thenReturn(PATH);
        when(req.getMethod()).thenReturn(METHOD);

        requestHandler = new RequestHandler();
    }

    @Test
    void makeRequestTest() throws IOException {
        requestHandler.makeRequest(req, resp, logger);
        verify(logger).log(String.format("Received %s request for %s", METHOD, PATH));
        verify(logger).log(String.format("Sending HTTP response: %s", MESSAGE));
        verify(logger).logParameters(req);
        verify(logger).logBody(req);
        verify(logger).logHeaders(req);
        verify(logger).log("Response sent");
    }
}