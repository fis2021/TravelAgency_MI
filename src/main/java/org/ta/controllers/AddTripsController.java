package org.ta.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.ta.services.TripService;

import java.util.Objects;

public class AddTripsController {

    @FXML
    private TextField locationField;
    @FXML
    private TextField periodField;
    @FXML
    private TextField priceField;
    @FXML
    private Text addMessage;

    public void handleAddInDatabase()  {
        try {
            TripService.checkLocationDoesNotAlreadyExist(locationField.getText());
            TripService.addTrip(locationField.getText(), periodField.getText(), priceField.getText());
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("travelAgent_home.fxml")));
            Stage window = (Stage) addMessage.getScene().getWindow();
            window.setScene(new Scene(root, 600, 400));
        }catch (Exception e){
            addMessage.setText(e.getMessage());
        }

    }

    public void handleGoBackButton(javafx.event.ActionEvent login)throws Exception{
        Parent root1 = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("travelAgent_home.fxml")));
        Stage window = (Stage) ((Node) login.getSource()).getScene().getWindow();;
        window.setTitle("Travel Agent");
        window.setScene(new Scene(root1, 600, 400));
        window.show();
    }
}
