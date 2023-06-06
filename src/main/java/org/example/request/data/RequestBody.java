package org.example.request.data;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class RequestBody implements Parseable<String> {
    private final String body;

    public RequestBody(HttpServletRequest request) {
        try {
            this.body = parse(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String parse (HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();
        StringBuilder body = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            body.append(line);
        }
        return body.toString();
    }

    public void sendBody(HttpServletResponse response, String message) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.print(message);
    }

    public String getBody() {
        return body;
    }
}
