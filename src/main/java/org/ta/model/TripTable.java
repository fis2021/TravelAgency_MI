package org.ta.model;

import javafx.beans.property.SimpleStringProperty;
import org.ta.controllers.LoginController;

import java.util.Objects;

public class TripTable {

    private SimpleStringProperty location;
    private SimpleStringProperty period;
    private SimpleStringProperty price;
    private String book;
    private String allset;

    public TripTable(String location,String period,String price){
        this.location=new SimpleStringProperty(location);
        this.period=new SimpleStringProperty(period);
        this.price=new SimpleStringProperty(price);
        this.book="0";
        this.allset= LoginController.getLoggedUser();

    }
    public TripTable(){}

    public SimpleStringProperty getLocation() {
        return location;
    }

    public void setLocation(SimpleStringProperty location) {
        this.location = location;
    }

    public SimpleStringProperty getPrice() {
        return price;
    }

    public void setPrice(SimpleStringProperty price) {
        this.price = price;
    }

    public SimpleStringProperty getPeriod() {
        return period;
    }

    public void setPeriod(SimpleStringProperty period) {
        this.period = period;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getAllset() {
        return allset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripTable tripTable = (TripTable) o;
        return location.equals(tripTable.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location);
    }
}
