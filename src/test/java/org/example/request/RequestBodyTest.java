package org.example.request;

import static org.junit.jupiter.api.Assertions.assertEquals;

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