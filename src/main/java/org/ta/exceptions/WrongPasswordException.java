package org.ta.exceptions;

public class WrongPasswordException extends Exception{
    public WrongPasswordException() {
        super(String.format("Wrong password ! "));
    }

}