package org.example.request.data;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface Parseable<T> {
    T parse(HttpServletRequest req)  throws IOException;
}
