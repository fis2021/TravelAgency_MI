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
import org.ta.exceptions.TripAlreadyBookedException;
import org.ta.model.Trip;
import org.ta.services.TripService;

import java.util.List;

public class CustomerHomeController {

    @FXML
    private TableView<Trip> offersTable;
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

        offersTable.setItems(categories);
    }
    private ObservableList<Trip> categories = FXCollections.observableArrayList(TripService.getAllTrips());
    public List<Trip> getTripsFromTable() {
        return offersTable.getItems();
    }

    /*@FXML
    public void bookTrip(Trip trip)throws TripAlreadyBookedException {
        trip.setBook(LoginController.getLoggedUser());
        if(trip.getBook().equals("0"))
            throw new TripAlreadyBookedException();
    }*/

    public void bookTrip() {
        ObservableList<Trip> singleTrip;
        singleTrip=offersTable.getSelectionModel().getSelectedItems();

        singleTrip.get(0).setBook(LoginController.getLoggedUser());

    }

    @FXML
    public void goBackToLoginScene(javafx.event.ActionEvent login)throws Exception{
        Parent root1 = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        Stage window = (Stage) ((Node) login.getSource()).getScene().getWindow();;
        window.setTitle("Login");
        window.setScene(new Scene(root1, 600, 400));
        window.show();
    }





}
