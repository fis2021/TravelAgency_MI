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
import org.ta.exceptions.UsernameAlreadyExistsException;
import org.ta.model.Trip;
import org.ta.services.TripService;
import org.ta.services.UserService;

import java.util.List;

public class MyOrdersController {

    @FXML
    private TableView<Trip> myOrdersTable;
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

        myOrdersTable.setItems(trips);
    }
    private ObservableList<Trip> trips = FXCollections.observableArrayList(TripService.getMyBookedTrips());

    @FXML
    public void goBackToLoginScene(javafx.event.ActionEvent login)throws Exception{
        Parent root1 = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        Stage window = (Stage) ((Node) login.getSource()).getScene().getWindow();;
        window.setTitle("Travel Agency");
        window.setScene(new Scene(root1, 600, 400));
        window.show();
    }

    @FXML
    public void goToTravelAgentHome(javafx.event.ActionEvent login)throws Exception{
        Parent root1 = FXMLLoader.load(getClass().getClassLoader().getResource("travelAgent_home.fxml"));
        Stage window = (Stage) ((Node) login.getSource()).getScene().getWindow();;
        window.setTitle("Travel Agent Page");
        window.setScene(new Scene(root1, 600, 400));
        window.show();
    }

    @FXML
    public void goToRecommendation(javafx.event.ActionEvent login)throws Exception{
        Parent root1 = FXMLLoader.load(getClass().getClassLoader().getResource("recommendation.fxml"));
        Stage window = (Stage) ((Node) login.getSource()).getScene().getWindow();
        window.setTitle("Recommendation");
        window.setScene(new Scene(root1, 600, 400));
        window.show();
    }

    @FXML
    public void allSetTrip() {
        ObservableList<Trip> singleTrip,allTrips;
        singleTrip=myOrdersTable.getSelectionModel().getSelectedItems();
        TripService.AllSetThisTrip(singleTrip.get(0));
        allTrips= myOrdersTable.getItems();
        singleTrip.forEach(allTrips::remove);
    }


}

