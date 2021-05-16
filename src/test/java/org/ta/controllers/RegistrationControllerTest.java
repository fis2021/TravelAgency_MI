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
class RegistrationControllerTest {
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
    void testRegister(FxRobot robot) throws UsernameAlreadyExistsException {
        robot.clickOn("#register");

        robot.clickOn("#username");
        robot.write("customer");
        robot.clickOn("#password");
        robot.write("password");
        robot.clickOn("#role");
        robot.type(KeyCode.DOWN);
        robot.type(KeyCode.ENTER);
        robot.clickOn("#register");
        assertThat(robot.lookup("#text").queryText()).hasText(String.format("                    Account created successfully!"));
    }

    @Test
    void testRedirectToLogin(FxRobot robot) {
        robot.clickOn("#register");
        robot.clickOn("#login");
    }
}