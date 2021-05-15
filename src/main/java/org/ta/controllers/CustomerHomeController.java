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
import org.ta.exceptions.LocationAlreadyExistsException;
import org.ta.exceptions.TripAlreadyBookedException;
import org.ta.model.Trip;
import org.ta.services.TripService;

import java.util.List;
import java.util.Objects;

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
    private ObservableList<Trip> categories = FXCollections.observableArrayList(TripService.getAllTripsCustomer());
    public List<Trip> getTripsFromTable() {
        return offersTable.getItems();
    }


    public void bookTrip() {
        ObservableList<Trip> singleTrip,allTrips;
        singleTrip=offersTable.getSelectionModel().getSelectedItems();

        TripService.BookThisTrip(singleTrip.get(0));

        allTrips= offersTable.getItems();
        singleTrip.forEach(allTrips::remove);

    }

    @FXML
    public void goBackToLoginScene(javafx.event.ActionEvent login)throws Exception{
        Parent root1 = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        Stage window = (Stage) ((Node) login.getSource()).getScene().getWindow();
        window.setTitle("Login");
        window.setScene(new Scene(root1, 600, 400));
        window.show();
    }

    @FXML
    public void goToMyTrips(javafx.event.ActionEvent login)throws Exception{
        Parent root2 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("myTrips.fxml")));
        Stage window = (Stage) ((Node) login.getSource()).getScene().getWindow();;
        window.setTitle("My Trips");
        window.setScene(new Scene(root2, 600, 400));
        window.show();
    }

    private int counter=0;

    public void setLocationRec(int counter,String location){

        for(int i=1;i<=counter;i++){
            location=location+"?";
        }
    }

    private String locationRec="";

    public void handleRecommendationInDatabase() throws LocationAlreadyExistsException {
        counter++;

        setLocationRec(counter,locationRec);

        TripService.checkLocationDoesNotAlreadyExist(locationRec);
        TripService.addTrip("?", "?", "?");

    }





}
