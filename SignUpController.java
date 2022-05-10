package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.SignUpException;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;

public class SignUpController{

    @FXML
    private Button button_log_in;

    @FXML
    private Button button_signup;

    @FXML
    private TextField tf_username;

    @FXML
    private PasswordField tf_password;

    @FXML
    private TextField tf_firstname;

    @FXML
    private TextField tf_lastname;

    @FXML
    private TextField tf_email;

    @FXML
    private TextField tf_phone;

    @FXML
    private Text registrationMessage;

    @FXML
    private ChoiceBox role;

    @FXML
    public void initialize() {
        role.getItems().addAll("Client/Anticariat", "Seller");
    }

    @FXML
    private void handleSignUp() throws IOException{
        try {
            UserService.checkFilledInformations(tf_username.getText(), tf_password.getText(), (String) role.getValue(),tf_firstname.getText(), tf_lastname.getText(),tf_email.getText(),tf_phone.getText());
            UserService.addUser(tf_username.getText(), tf_password.getText(), (String) role.getValue(),tf_firstname.getText(), tf_lastname.getText(),tf_email.getText(),tf_phone.getText());

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Account created successfully!");
            alert.show();
        } catch (UsernameAlreadyExistsException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        } catch (SignUpException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    @FXML
    private void handleLogin() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("hello.fxml"));
        Stage window = (Stage) button_log_in.getScene().getWindow();
        window.setTitle("LogIn!");
        window.setScene(new Scene(root,600,400));
    }
}
