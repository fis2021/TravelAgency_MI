package org.ta.model;

import org.dizitart.no2.objects.Id;

import java.util.Objects;

public class Trip {
    @Id
    private String location;
    private String imgSrc;
    private double price;
    private String period;

    public Trip(String location, String imgSrc, double price, String period) {
        this.location = location;
        this.imgSrc = imgSrc;
        this.price = price;
        this.period = period;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trip trip = (Trip) o;
        return Double.compare(trip.price, price) == 0 && Objects.equals(location, trip.location) && Objects.equals(imgSrc, trip.imgSrc) && Objects.equals(period, trip.period);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, imgSrc, price, period);
    }
}