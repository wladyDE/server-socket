package org.example.http_request;

import org.example.http_request.request_data.RequestBody;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RequestBodyTest {
/*    @Test
    void parseBody() throws IOException {
        HttpServletRequest req = mock(HttpServletRequest.class);

        BufferedReader reader = mock(BufferedReader.class);
        when(req.getReader()).thenReturn(reader);
        when(reader.readLine())
                .thenReturn("line1")
                .thenReturn("line2")
                .thenReturn(null);

        String result = RequestBody.parseBody(req);

        assertEquals("line1line2", result);
    }

    @Test
    void sendBody() throws IOException {
        HttpServletResponse resp = mock(HttpServletResponse.class);
        PrintWriter printWriter = mock(PrintWriter.class);

        when(resp.getWriter()).thenReturn(printWriter);

        RequestBody.sendBody(resp, "message");

        verify(resp.getWriter()).print("message");
    }*/
}