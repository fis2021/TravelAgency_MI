package org.ta.exceptions;

public class LocationAlreadyExistsException extends Exception {

    private String location;

    public LocationAlreadyExistsException(String location) {
        super(String.format("     A package with this location already exists!"));
        this.location = location;
    }

    public String getUsername() {
        return location;
    }
}
