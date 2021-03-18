package com.example.autogarage.exceptions;



//Als de gebruiker niet gevonden wordt krijg je deze exception
public class UsernameNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public UsernameNotFoundException(String username) {
        super("Cannot find user " + username);
    }

}