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

import org.ta.exceptions.UsernameDoesNotExistException;
import org.ta.exceptions.WrongPasswordException;
import org.ta.services.UserService;

import java.util.Objects;

public class LoginController {

    @FXML
    private Text wrongLogIn;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;

    private String userRole;
    private static String loggedUser;


    @FXML
    public void handleLoginAction(javafx.event.ActionEvent TripsPageInterface) throws Exception {
        try {
           wrongLogIn.setText("Login successfully!");
            loggedUser = UserService.getLoggedUser(usernameField.getText());
            userRole = UserService.getUserRole(usernameField.getText());
            if(UserService.checkUserCredentials(usernameField.getText(), passwordField.getText()).equals("Travel Agent")){
                Parent adminInterface = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("travelAgent_home.fxml")));
                Stage window = (Stage) ((Node) TripsPageInterface.getSource()).getScene().getWindow();
                window.setTitle("Travel Agent Page");
                window.setScene(new Scene(adminInterface, 600, 400));
                window.show();
            }
            else{
                Parent customerInterface = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("customer_home.fxml")));
                Stage window = (Stage) ((Node) TripsPageInterface.getSource()).getScene().getWindow();;
                window.setTitle("Customer Page");
                window.setScene(new Scene(customerInterface, 600, 400));
                window.show();

            }
        } catch (UsernameDoesNotExistException e) {
            wrongLogIn.setText(e.getMessage());
        } catch (WrongPasswordException e){
            wrongLogIn.setText(e.getMessage());
        }
    }
    public void goBackToResgisterScene(javafx.event.ActionEvent login)throws Exception{
        Parent root1 = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
        Stage window = (Stage) ((Node) login.getSource()).getScene().getWindow();;
        window.setTitle("Registration");
        window.setScene(new Scene(root1, 600, 400));
        window.show();
    }

    public static String getLoggedUser(){
        return loggedUser;
    }
}