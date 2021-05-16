package org.ta.controllers;

import javafx.scene.input.KeyCode;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ta.services.FileSystemService;
import org.ta.services.UserService;
import org.testfx.api.FxRobot;

import javax.xml.stream.XMLStreamReader;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.assertions.api.Assertions.assertThat;
import org.junit.jupiter.api.*;

class LoginControllerTest {
    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration";
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        UserService.initDatabase();
    }

    @Test
    void testLogin(FxRobot robot) {
        robot.clickOn("#username");
        robot.write("agent");
        robot.clickOn("#password");
        robot.write("password1");

        robot.clickOn("#login");
        assertThat(robot.lookup("#wrongLogin").queryText()).hasText(String.format("    An account with this username does not exist!"));
    }





}

