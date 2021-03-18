package com.example.autogarage.model;

import java.io.Serializable;

//Hier wordt een string(serializable) deze string wordt als een aparte key gebruikt
public class AuthorityKey implements Serializable {
    private String username;
    private String authority;
}