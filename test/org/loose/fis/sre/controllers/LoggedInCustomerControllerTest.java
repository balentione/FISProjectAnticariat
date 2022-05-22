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
class LoggedInCustomerControllerTest {

    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-login_customer";
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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("logged-inCustomer.fxml"));
        primaryStage.setScene(new Scene(root,600,400));
        primaryStage.show();
    }

    @Test
    @DisplayName("Functionalitatea butonului -View Books-")
    void testViewBooksButton(FxRobot robot) {
        robot.clickOn("#viewBooks_button");
    }

    @Test
    @DisplayName("Functionalitatea butonului -View buyed books-")
    void testViewBuyedBooksButton(FxRobot robot) {
        robot.clickOn("#viewBuyedBooks_button");
    }

    @Test
    @DisplayName("Functionalitatea butonului de LogOut")
    void testLogOutButton(FxRobot robot) {
        robot.clickOn("#LogOut_button");
    }
}