package org.loose.fis.sre.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.exceptions.WrongCredentialsException;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;

public class HelloController {
    @FXML
    private Text registrationMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private ChoiceBox role;
    @FXML
    private Button button_sign_up;
    @FXML
    private Button button_log_in;

    @FXML
    public void initialize() {
        role.getItems().addAll("Client/Anticariat", "Seller");
    }

    @FXML
    private void handleSignUp() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sign-up.fxml"));
        Stage window = (Stage) button_sign_up.getScene().getWindow();
        window.setTitle("SignUp!");
        window.setScene(new Scene(root,600,400));
    }

    @FXML
    private void handleLogin() throws IOException  {
        try {
            UserService.checkCredentials(usernameField.getText(), passwordField.getText(), (String) role.getValue());

            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("logged-in.fxml"));
            Stage window = (Stage) button_log_in.getScene().getWindow();
            window.setTitle("LoggedIn!");
            window.setScene(new Scene(root,600,400));
        } catch (WrongCredentialsException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }
}
