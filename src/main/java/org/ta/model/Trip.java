package org.ta.model;

import org.dizitart.no2.objects.Id;
import org.ta.controllers.LoginController;

import java.util.Objects;

public class Trip {
    @Id
    private String location;
    private String price;
    private String period;
    private String book;
    private String allset;


    public Trip(String location,  String period, String price) {
        this.location = location;
        this.price = price;
        this.period = period;
        this.book = "0";
        this.allset= LoginController.getLoggedUser();
    }

    public Trip(){}

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getAllSet() {
        return allset;
    }

    public void setAllSet(String allset) {
        this.allset = allset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return Objects.equals(location, trip.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, period, price, book,allset);
    }
}