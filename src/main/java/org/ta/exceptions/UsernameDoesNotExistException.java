package org.ta.exceptions;

public class UsernameDoesNotExistException extends Exception {
    private String username;

    public UsernameDoesNotExistException(String username){
        super(String.format("    An account with this username does not exist!"));
        this.username = username;
    }

    public String getUsername(){
        return username;
    }
}