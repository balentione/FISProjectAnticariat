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
import org.testfx.service.query.PointQuery;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)
class ViewBooksControllerTest {
    @BeforeEach
    void setUp() throws Exception {
        FileSystemService.APPLICATION_FOLDER = ".test-viewbook";
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
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("viewBooks.fxml"));
        primaryStage.setScene(new Scene(root,600,400));
        primaryStage.show();
    }

    /*@Test
    @DisplayName("Functionalitatea butonului de Back")
    void testBackButton(FxRobot robot) {
        robot.clickOn("#back_button");
    }

    @Test
    @DisplayName("Functionalitatea butonului de Delete")
    void testDeleteButton(FxRobot robot) {
        UserService.addBook("username","SF","titlu","autor","200","Good");
        robot.clickOn((PointQuery) robot.lookup("#books_table").nth(1).query());
        robot.clickOn("#delete_button");
    }*/

}