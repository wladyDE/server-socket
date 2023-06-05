package org.example.request.data;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class RequestBody implements Parseable<String> {
    @Override
    public String parse (HttpServletRequest req) throws IOException {
        BufferedReader reader = req.getReader();
        StringBuilder body = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            body.append(line);
        }
        return body.toString();
    }

    public void sendBody(HttpServletResponse resp, String message) throws IOException {
        PrintWriter writer = resp.getWriter();
        writer.print(message);
    }
}
