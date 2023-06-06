package org.example.request.data;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
@Getter
public class RequestData {
    private Map<String, String[]> parameters;
    private String body;
    private Map<String, String> headers;
}
