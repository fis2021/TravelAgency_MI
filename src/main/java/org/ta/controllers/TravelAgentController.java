package org.ta.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.ta.model.TripTable;
import org.ta.services.TripService;

import java.util.List;

public class TravelAgentController {


public class TravelAgentController {

    @FXML
    private TableView<TripTable> tableView;
    @FXML
    private TableColumn<TripTable,String> locationColumn;
    @FXML
    private TableColumn<TripTable,String> periodColumn;
    @FXML
    private TableColumn<TripTable,String> priceColumn;


    @FXML
    public void handleAdd(javafx.event.ActionEvent login)throws Exception{
        Parent root1 = FXMLLoader.load(getClass().getClassLoader().getResource("addTrips.fxml"));
        Stage window = (Stage) ((Node) login.getSource()).getScene().getWindow();;
        window.setTitle("Add Form");
        window.setScene(new Scene(root1, 600, 400));
        window.show();
    }
    @FXML
    public void handleDelete() throws Exception{

    }

    @FXML
    public void goBackToLoginScene(javafx.event.ActionEvent login)throws Exception{
        Parent root1 = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        Stage window = (Stage) ((Node) login.getSource()).getScene().getWindow();;
        window.setTitle("Login");
        window.setScene(new Scene(root1, 600, 400));
        window.show();
    }

    @FXML
    public void handleOrders(javafx.event.ActionEvent login)throws Exception{
        Parent root1 = FXMLLoader.load(getClass().getClassLoader().getResource("myOrders.fxml"));
        Stage window = (Stage) ((Node) login.getSource()).getScene().getWindow();;
        window.setTitle("My Orders");
        window.setScene(new Scene(root1, 600, 400));
        window.show();
    }

    private String Username;
    public void populateDataFromLogInTravelAgent(String username){
        Username=username;

        locationColumn.setCellValueFactory(new PropertyValueFactory<>("Location"));
        periodColumn.setCellValueFactory(new PropertyValueFactory<>("Period"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("Price"));


        List<TripTable> packages= TripService.getAllTrips(Username);

        for(TripTable trips:packages){
            tableView.getItems().add(new TripTable(trips.getLocation(),trips.getPeriod(), trips.getPrice()));
        }
    }

}
