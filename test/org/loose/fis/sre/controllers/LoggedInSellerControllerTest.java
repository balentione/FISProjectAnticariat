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
class LoggedInSellerControllerTest {

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-login_seller";
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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("logged-inSeller.fxml"));
        primaryStage.setScene(new Scene(root,600,400));
        primaryStage.show();
    }

    @Test
    @DisplayName("Functionalitatea butonului -Add New-")
    void testAddNewButton(FxRobot robot) {
        robot.clickOn("#add_button");
    }

    @Test
    @DisplayName("Functionalitatea butonului -View Books-")
    void testViewBooksButton(FxRobot robot) {
        robot.clickOn("#viewBooks_button");
    }

    @Test
    @DisplayName("Functionalitatea butonului -View Offers-")
    void testViewOffersButton(FxRobot robot) {
        robot.clickOn("#viewOffers_button");
    }

    @Test
    @DisplayName("Functionalitatea butonului -View Solds-")
    void testViewSoldsButton(FxRobot robot) {
        robot.clickOn("#viewSolds_button");
    }

    @Test
    @DisplayName("Functionalitatea butonului de LogOut")
    void testLogOutButton(FxRobot robot) {
        robot.clickOn("#LogOut_button");
    }
}