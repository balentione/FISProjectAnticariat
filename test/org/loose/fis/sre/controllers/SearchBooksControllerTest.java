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
class SearchBooksControllerTest {
    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-searchbook";
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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("searchBooks.fxml"));
        primaryStage.setScene(new Scene(root,600,400));
        primaryStage.show();
    }

    /*@Test
    @DisplayName("Functionalitatea butonului de Back")
    void testBackButton(FxRobot robot) {
        robot.clickOn("#back_button");
    }

    @Test
    @DisplayName("Functionalitatea butonului de Buy")
    void testBuyButton(FxRobot robot) {
        robot.clickOn("#back_button");
    }*/
}