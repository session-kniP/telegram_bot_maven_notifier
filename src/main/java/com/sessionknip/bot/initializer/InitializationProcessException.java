package com.sessionknip.bot.initializer;

public class InitializationProcessException extends Exception {

    public InitializationProcessException(String message) {
        super(message);
    }

    public InitializationProcessException(String message, Throwable cause) {
        super(message, cause);
    }
}
