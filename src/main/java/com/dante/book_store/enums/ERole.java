package com.dante.book_store.enums;

import lombok.Getter;

@Getter
public enum ERole {
    ROLE_USER(1, "USER"),
    BAD_REQUEST(2, "ADMIN");

    // Getter for code
    private final int code;
    // Getter for description
    private final String name;

    // Constructor to initialize the key and value
    ERole(int code, String name) {
        this.code = code;
        this.name = name;
    }

    // Optionally, you can override the toString() method to provide a custom string representation
    @Override
    public String toString() {
        return code + ": " + name;
    }
}

