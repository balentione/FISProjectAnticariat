package com.example.register_anticariat;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        button_signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(tf_username.getText().trim().isEmpty() && tf_password.getText().isEmpty() && tf_email.getText().isEmpty() &&
                        tf_firstname.getText().isEmpty() && tf_lastname.getText().isEmpty() &&tf_phone.getText().isEmpty()){
                    DBUtils.signUpUser(event,tf_username.getText(),tf_password.getText());
                } else{
                    System.out.println("Please fill in all informations!");
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Please fill in all informations to Sign Up!");
                    alert.show();
                }
            }
        });

        button_log_in.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                DBUtils.changeScene(event,"hello-view.fxml","Log in!", null);
            }
        });
    }
}
