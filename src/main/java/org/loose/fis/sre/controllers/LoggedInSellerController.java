package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.UserService;

import java.awt.*;
import java.io.IOException;

public class LoggedInSellerController {
    @FXML
    private Button add_button;

    @FXML
    private Button viewBooks_button;

    @FXML
    private Button viewOffers_button;

    @FXML
    private Button LogOut_button;

    @FXML
    private Label label_hello;

    @FXML
    public void initialize() {
        label_hello.setText("Hello " + User.getLast_username() + "!");
    }

    @FXML
    private void handleAdd() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("addBooks.fxml"));
        Stage window = (Stage) add_button.getScene().getWindow();
        window.setTitle("Publish a book!");
        window.setScene(new Scene(root,600,400));
    }

    @FXML
    private void handleViewBooks() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("viewBooks.fxml"));
        Stage window = (Stage) viewBooks_button.getScene().getWindow();
        window.setTitle("Your books");
        window.setScene(new Scene(root,600,400));
    }

    @FXML
    private void handleViewOffers() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("viewMakedOffers.fxml"));
        Stage window = (Stage) viewOffers_button.getScene().getWindow();
        window.setTitle("Your offers");
        window.setScene(new Scene(root,600,400));
    }

    @FXML
    private void handleLogout() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("hello.fxml"));
        Stage window = (Stage) LogOut_button.getScene().getWindow();
        window.setTitle("LogIn!");
        window.setScene(new Scene(root,600,400));
    }

}
