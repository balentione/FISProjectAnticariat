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


@ExtendWith(ApplicationExtension.class)
class HelloControllerTest {
    public static final String FNAME = "fname";
    public static final String LNAME = "lname";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String CLIENT_ROLE = "Client/Anticariat";
    private static final String SELLER_ROLE = "Seller";

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-login";
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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("hello.fxml"));
        primaryStage.setScene(new Scene(root,600,400));
        primaryStage.show();
    }

    @Test
    @DisplayName("Testare login pentru client/anticariat")
    void testClientLogin(FxRobot robot) throws Exception{
        UserService.addUser(USERNAME,PASSWORD,CLIENT_ROLE,FNAME,LNAME,EMAIL,PHONE);
        robot.clickOn("#usernameField");
        robot.write(USERNAME);
        robot.clickOn("#passwordField");
        robot.write(PASSWORD);
        robot.clickOn("#role");
        robot.clickOn("Client/Anticariat");
        robot.clickOn("#button_log_in");
    }

    @Test
    @DisplayName("Testare login pentru seller")
    void testSellerLogin(FxRobot robot) throws Exception{
        UserService.addUser(USERNAME,PASSWORD,SELLER_ROLE,FNAME,LNAME,EMAIL,PHONE);
        robot.clickOn("#usernameField");
        robot.write(USERNAME);
        robot.clickOn("#passwordField");
        robot.write(PASSWORD);
        robot.clickOn("#role");
        robot.clickOn("Seller");
        robot.clickOn("#button_log_in");
    }





}