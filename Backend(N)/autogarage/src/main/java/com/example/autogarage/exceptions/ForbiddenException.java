package com.example.autogarage.exceptions;


//bij geen authentication krijg je deze exception
public class ForbiddenException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ForbiddenException() {
        super("Je hebt niet de juiste authorisatie voor deze handeling");
    }
}
