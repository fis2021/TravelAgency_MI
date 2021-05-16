package org.ta.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.ta.exceptions.UsernameAlreadyExistsException;
import org.ta.services.FileSystemService;
import org.ta.services.TripService;
import org.ta.services.UserService;

import org.testfx.api.FxRobot;

import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.testfx.assertions.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
class LoginControllerTest {
    public static final String USERNAMECOMPANY = "company";
    public static final String USERNAMECUSTOMER = "customer";
    public static final String PASSWORD = "password";

    @AfterEach
    void tearDown() {
        UserService.getDatabase().close();
        TripService.getDatabase().close();
    }

    @BeforeEach
    void setUp() throws Exception{
        FileSystemService.APPLICATION_FOLDER=".test";
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
        TripService.initDatabase();
    }

    @Start
    void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    @Test
    void testLoginCompany(FxRobot robot) throws UsernameAlreadyExistsException{
        UserService.addUser(USERNAMECOMPANY,PASSWORD,"Travel Agent");
        robot.clickOn("#username");
        robot.write(USERNAMECOMPANY);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#login");
    }

    @Test
    void testLoginCustomer(FxRobot robot) throws UsernameAlreadyExistsException {
        UserService.addUser(USERNAMECUSTOMER,PASSWORD,"Customer");
        robot.clickOn("#username");
        robot.write(USERNAMECUSTOMER);
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#login");
    }

    @Test
    void testCustomerCanNotEnterInvalidCredentials(FxRobot robot) throws UsernameAlreadyExistsException{
        UserService.addUser(USERNAMECUSTOMER,PASSWORD,"Customer");;
        robot.clickOn("#username");
        robot.write("customerWRONG");
        robot.clickOn("#password");
        robot.write(PASSWORD);
        robot.clickOn("#login");
        assertThat(robot.lookup("#wrongLogin").queryText()).hasText(String.format("    An account with this username does not exist!"));
    }

    @Test
    void testRedirectToRegister(FxRobot robot) {
        robot.clickOn("#register");
    }

    @Test
    void testWrongPassword(FxRobot robot) throws UsernameAlreadyExistsException {
        UserService.addUser(USERNAMECUSTOMER,PASSWORD,"Customer");;
        robot.clickOn("#username");
        robot.write("customer");
        robot.clickOn("#password");
        robot.write("wrong");
        robot.clickOn("#login");
        assertThat(robot.lookup("#wrongLogin").queryText()).hasText(String.format("    Wrong password ! "));
    }
}