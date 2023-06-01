package org.example.http_request.request_data;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface Parseable<T> {
    T parse(HttpServletRequest req)  throws IOException;
}
