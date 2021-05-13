package org.ta.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CustomerHomeController {/*
    public void goBackToLoginScene(javafx.event.ActionEvent login)throws Exception{
        Parent root1 = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        Stage window = (Stage) ((Node) login.getSource()).getScene().getWindow();;
        window.setTitle("Login");
        window.setScene(new Scene(root1, 600, 400));
        window.show();
    }

    public void bookTrip(){

    }
    @FXML
    private TableView<TeacherSubjects> tableView;
    @FXML
    private TableColumn<TeacherSubjects,String> colSubject;
    @FXML
    private TextField addSubject;


    private String teacherUsername;

    @FXML
    public void switchToLogIn() throws Exception {
        Main.setRoot("login");
    }

    @FXML
    public void switchToSubjects() throws Exception {
        Main.setRoot("teacher2");

        TeacherSubjectsController controller=Main.getPath().getController();
        ObservableList<TeacherSubjects> subject;
        subject=tableView.getSelectionModel().getSelectedItems();
        controller.setHelloMessage(subject.get(0).getSubjectName());
        controller.populateDataFromDashboard(teacherUsername,subject.get(0).getSubjectName());
    }

    public void populateDataFromLogIn(String username){
        teacherUsername=username;
        colSubject.setCellValueFactory(new PropertyValueFactory<>("SubjectName"));

        LinkedList<String> teacherSubject=CatalogService.teacherSubjects(teacherUsername);

        for(String subject:teacherSubject){
            tableView.getItems().add(new TeacherSubjects(subject));
        }
    }

    public void handleAddingSubject(){
        CatalogService.addTeacher_Subject(teacherUsername,addSubject.getText());
        tableView.getItems().add(new TeacherSubjects(addSubject.getText()));
    }

    public void handleRemovingSubject(){

        ObservableList<TeacherSubjects> allSubjects,singleSubjects;
        singleSubjects=tableView.getSelectionModel().getSelectedItems();
        CatalogService.clearSubject(teacherUsername,singleSubjects.get(0).getSubjectName());

        allSubjects= tableView.getItems();
        singleSubjects.forEach(allSubjects::remove);
    }
*/
}
