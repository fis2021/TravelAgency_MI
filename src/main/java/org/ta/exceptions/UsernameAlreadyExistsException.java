package org.ta.exceptions;

public class UsernameAlreadyExistsException extends Exception {

    private String username;

    public UsernameAlreadyExistsException(String username) {
        super(String.format("     An account with this username already exists!"));
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
