package org.ta.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.ta.model.Trip;
import org.ta.services.TripService;

import java.util.List;
import java.util.Objects;

public class TravelAgentController {

    @FXML
    private TableView<Trip> tripsTable;
    @FXML
    private TableColumn<Trip,String> locationColumn;
    @FXML
    private TableColumn<Trip,String> periodColumn;
    @FXML
    private TableColumn<Trip,String> priceColumn;

    public void initialize() {
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("Location"));
        periodColumn.setCellValueFactory(new PropertyValueFactory<>("Period"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));
        tripsTable.setItems(categories);
    }

    private ObservableList<Trip> categories = FXCollections.observableArrayList(TripService.getAllTrips());

    @FXML
    public void handleRemoveTrip() {
        ObservableList<Trip> allTrips,singleTrip;
        singleTrip=tripsTable.getSelectionModel().getSelectedItems();
        TripService.clearTrip(singleTrip.get(0).getLocation(),singleTrip.get(0).getPeriod(),singleTrip.get(0).getPrice());
        allTrips= tripsTable.getItems();
        singleTrip.forEach(allTrips::remove);
    }

    @FXML
    public void goBackToLoginScene(javafx.event.ActionEvent login)throws Exception{
        Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("login.fxml")));
        Stage window = (Stage) ((Node) login.getSource()).getScene().getWindow();
        window.setTitle("Login");
        window.setScene(new Scene(root1, 600, 400));
        window.show();
    }

    @FXML
    public void handleOrders(javafx.event.ActionEvent login)throws Exception{
        Parent root2 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("myOrders.fxml")));
        Stage window = (Stage) ((Node) login.getSource()).getScene().getWindow();;
        window.setTitle("My Orders");
        window.setScene(new Scene(root2, 600, 400));
        window.show();
    }

    @FXML
    public void handleAddTripButton(javafx.event.ActionEvent login)throws Exception{
        Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("addTrips.fxml")));
        Stage window = (Stage) ((Node) login.getSource()).getScene().getWindow();;
        window.setTitle("Add Form");
        window.setScene(new Scene(root1, 600, 400));
        window.show();
    }
}

