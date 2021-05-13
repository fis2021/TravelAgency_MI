package org.ta.exceptions;

public class LocationAlreadyExistsException extends Exception {

    private String location;

    public LocationAlreadyExistsException(String location) {
        super(String.format("     An account with this username already exists!"));
        this.location = location;
    }

    public String getUsername() {
        return location;
    }
}
