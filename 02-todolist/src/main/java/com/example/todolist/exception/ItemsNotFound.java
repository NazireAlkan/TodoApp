package com.example.todolist.exception;

public class ItemsNotFound extends RuntimeException {

    public ItemsNotFound(String message) {
        super(message);
    }

    public ItemsNotFound(String message, Throwable cause) {
        super(message, cause);
    }

    public ItemsNotFound(Throwable cause) {
        super(cause);
    }
}
