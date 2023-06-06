package org.example.request.method;

public enum Method {
    GET ("GET"),
    POST ("POST"),
    PUT ("PUT"),
    DELETE ("DELETE");

    private final String methodName;

    Method(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodName() {
        return methodName;
    }
}
