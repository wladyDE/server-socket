package org.example.exception;

public class NotExistingUserException extends Exception {
    @Override
    public String toString() {
        return "Such user does not exist!";
    }
}
