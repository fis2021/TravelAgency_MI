package org.ta.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TravelAgentController {


public class TravelAgentController {

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

    private String studentUsername;
    public void populateDataFromLogInTravelAgent(String username){
        studentUsername=username;

        studentUsername.getLocation().setCellValueFactory(new PropertyValueFactory<>("Location"));
        studentUsername.getPeriod().setCellValueFactory(new PropertyValueFactory<>("Period"));
        studentUsername.getPrice().setCellValueFactory(new PropertyValueFactory<>("Price"));


        LinkedList<Pair<String,String>> subejcts_teachers= CatalogService.studentSubjectsTeachers(studentUsername);

        for(Pair<String,String> subjects:subejcts_teachers){
            tableView.getItems().add(new StudentTable(subjects.getKey(),subjects.getValue()));
        }
    }

}
