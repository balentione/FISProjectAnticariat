package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.loose.fis.sre.model.User;

import java.io.IOException;

public class LoggedInCustomerController {

    @FXML
    private Button viewBooks_button;

    @FXML
    private Button viewBuyedBooks_button;

    @FXML
    private Button LogOut_button;

    @FXML
    private Label label_hello;

    @FXML
    public void initialize() {
        label_hello.setText("Hello " + User.getLast_username() + "!");
    }

    @FXML
    private void handleViewBooks() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("viewBooks.fxml")); //de modificat
        Stage window = (Stage) viewBooks_button.getScene().getWindow();
        window.setTitle("Published books");
        window.setScene(new Scene(root,600,400));
    }

    @FXML
    private void handleViewBuyedBooks() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("viewOffers.fxml")); //de modificat
        Stage window = (Stage) viewBuyedBooks_button.getScene().getWindow();
        window.setTitle("Your buyed books");
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
