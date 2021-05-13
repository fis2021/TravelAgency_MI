package org.ta.model;

import org.dizitart.no2.objects.Id;

import java.util.Objects;

public class Trip {
    @Id
    private String location;
    private String imgSrc;
    private double price;
    private String period;
    private boolean book;

    public Trip(String location, String imgSrc, double price, String period, boolean book) {
        this.location = location;
        this.imgSrc = imgSrc;
        this.price = price;
        this.period = period;
        this.book = book;
    }

    public Trip(){}

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public boolean getBook() {
        return book;
    }

    public void setBook(boolean book) {
        this.book = book;
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
        return Objects.hash(location, imgSrc, price, period, book);
    }
}