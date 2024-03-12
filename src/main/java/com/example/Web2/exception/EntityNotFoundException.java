package com.example.Web2.exception;

public class EntityNotFoundException extends RuntimeException{
    private Long id;
    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String message, Long id) {
        super(message);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
