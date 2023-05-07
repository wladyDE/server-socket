package org.example.request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

public class RequestBody {
    public static String parseBody (HttpServletRequest req) throws IOException {
        BufferedReader reader = req.getReader();
        StringBuilder body = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            body.append(line);
        }
        reader.close();
        return body.toString();
    }

    public static void sendBody(HttpServletResponse resp, String message) throws IOException {
        PrintWriter writer = resp.getWriter();
        writer.print(message);
        writer.flush();
        writer.close();
    }
}
