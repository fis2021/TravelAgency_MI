package org.ta.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.ta.model.Trip;
import org.ta.services.TripService;

import java.util.List;
import java.util.Objects;

public class MyTripsController {

    @FXML
    private TableView<Trip> myTripsTable;
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

        myTripsTable.setItems(categories);
    }

    private ObservableList<Trip> categories = FXCollections.observableArrayList(TripService.getMyTripsCustomer());

    @FXML
    public void goBackToLoginScene(javafx.event.ActionEvent login)throws Exception{
        Parent root1 = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        Stage window = (Stage) ((Node) login.getSource()).getScene().getWindow();
        window.setTitle("Travel Agency");
        window.setScene(new Scene(root1, 600, 400));
        window.show();
    }

    public void goToCustomerHome(javafx.event.ActionEvent login)throws Exception {
        Parent customerInterface = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("customer_home.fxml")));
        Stage window = (Stage) ((Node) login.getSource()).getScene().getWindow();
        window.setTitle("Customer Page");
        window.setScene(new Scene(customerInterface, 600, 400));
        window.show();
    }
}
