package org.ta.exceptions;

public class TripAlreadyBookedException extends Exception {

    public TripAlreadyBookedException() {
        super("The trip was already booked");

    }

}