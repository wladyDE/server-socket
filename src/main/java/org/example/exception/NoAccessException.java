package org.example.exception;

public class NoAccessException extends Exception {
    @Override
    public String toString() {
        return "Access denied. You do not have sufficient privileges to perform this action";
    }
}
