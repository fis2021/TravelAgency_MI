package org.ta.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.ta.exceptions.LocationAlreadyExistsException;
import org.ta.exceptions.UsernameAlreadyExistsException;
import org.ta.exceptions.UsernameDoesNotExistException;
import org.ta.exceptions.WrongPasswordException;
import org.ta.services.TripService;
import org.ta.services.UserService;

import java.util.Objects;

public class AddTripsController {
    @FXML
    private TextField locationField;
    @FXML
    private TextField periodField;
    @FXML
    private TextField priceField;

    private static String loggedUser;
    @FXML
    public void handleAddTripButton(javafx.event.ActionEvent TripsPageInterface) throws Exception {
        try {
            TripService.addTrip(locationField.getText(),  priceField.getText(), periodField.getText() );
        } catch (LocationAlreadyExistsException e){};

    }

    public static String getLoggedUser(){
        return loggedUser;
    }
}
