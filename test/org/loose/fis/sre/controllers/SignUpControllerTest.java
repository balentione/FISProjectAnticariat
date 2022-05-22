package org.loose.fis.sre.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class SignUpControllerTest {

    private static final String F_NAME = "fname";
    private static final String L_NAME = "lname";
    private static final String PHONE = "059035409";
    private static final String EMAIL = "email";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String CLIENT_ROLE = "Client/Anticariat";
    private static final String SELLER_ROLE = "Seller";



    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-registration";
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.APPLICATION_HOME_PATH.toFile());
        UserService.initDatabase();
    }
    @AfterEach
    void tearDown() {
        UserService.close();
    }

    @Start
    void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sign-up.fxml"));
        primaryStage.setScene(new Scene(root,600,400));
        primaryStage.show();
    }

    @Test
    @DisplayName("Creare cont de client")
    void testRegistrationClient(FxRobot robot) {
        robot.clickOn("#tf_firstname");
        robot.write(F_NAME);
        robot.clickOn("#tf_lastname");
        robot.write(L_NAME);
        robot.clickOn("#tf_phone");
        robot.write(PHONE);
        robot.clickOn("#tf_email");
        robot.write(EMAIL);
        robot.clickOn("#tf_username");
        robot.write(USERNAME);
        robot.clickOn("#tf_password");
        robot.write(PASSWORD);
        robot.clickOn("#role");
        robot.clickOn("Client/Anticariat");
        robot.clickOn("#button_signup");

        assertThat(UserService.getAllUsers().size()).isEqualTo(1);
        assertThat(UserService.getAllUsers().get(0).getFirst_name()).isEqualTo(F_NAME);
        assertThat(UserService.getAllUsers().get(0).getLast_name()).isEqualTo(L_NAME);
        assertThat(UserService.getAllUsers().get(0).getPhone()).isEqualTo(PHONE);
        assertThat(UserService.getAllUsers().get(0).getEmail()).isEqualTo(EMAIL);
        assertThat(UserService.getAllUsers().get(0).getUsername()).isEqualTo(USERNAME);
        assertThat(UserService.getAllUsers().get(0).getPassword()).isEqualTo(UserService.encodePassword(USERNAME, PASSWORD));
        assertThat(UserService.getAllUsers().get(0).getRole()).isEqualTo(CLIENT_ROLE);
    }

    @Test
    @DisplayName("Creare cont de seller")
    void testRegistrationSeller(FxRobot robot) {
        robot.clickOn("#tf_firstname");
        robot.write(F_NAME);
        robot.clickOn("#tf_lastname");
        robot.write(L_NAME);
        robot.clickOn("#tf_phone");
        robot.write(PHONE);
        robot.clickOn("#tf_email");
        robot.write(EMAIL);
        robot.clickOn("#tf_username");
        robot.write(USERNAME);
        robot.clickOn("#tf_password");
        robot.write(PASSWORD);
        robot.clickOn("#role");
        robot.clickOn("Seller");
        robot.clickOn("#button_signup");

        assertThat(UserService.getAllUsers().size()).isEqualTo(1);
        assertThat(UserService.getAllUsers().get(0).getFirst_name()).isEqualTo(F_NAME);
        assertThat(UserService.getAllUsers().get(0).getLast_name()).isEqualTo(L_NAME);
        assertThat(UserService.getAllUsers().get(0).getPhone()).isEqualTo(PHONE);
        assertThat(UserService.getAllUsers().get(0).getEmail()).isEqualTo(EMAIL);
        assertThat(UserService.getAllUsers().get(0).getUsername()).isEqualTo(USERNAME);
        assertThat(UserService.getAllUsers().get(0).getPassword()).isEqualTo(UserService.encodePassword(USERNAME, PASSWORD));
        assertThat(UserService.getAllUsers().get(0).getRole()).isEqualTo(SELLER_ROLE);
    }


}