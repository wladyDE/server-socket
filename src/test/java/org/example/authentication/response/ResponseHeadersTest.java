package org.example.authentication.response;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ResponseHeadersTest {
/*    @Mock
    private HttpServletResponse resp;

    @Test
    void setResponseHeaders() {
        doNothing().when(resp).setStatus(anyInt());
        doNothing().when(resp).setContentType(anyString());
        doNothing().when(resp).setContentLength(anyInt());

        ResponseHeaders.setResponseHeaders(resp, "message");

        verify(resp).setStatus(anyInt());
        verify(resp).setContentType(anyString());
        verify(resp).setContentLength(anyInt());
    }

    @Test
    void parseHeaders() {
        when(resp.getHeaderNames()).thenReturn(Collections.singletonList("key"));
        when(resp.getHeader(anyString())).thenReturn("value");

        Map<String, String> parameterMap = ResponseHeaders.parseHeaders(resp);

        assertEquals("value", parameterMap.get("key"));
    }*/
}