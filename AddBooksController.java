package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.dizitart.no2.NitriteId;
import org.loose.fis.sre.exceptions.PublishBookException;
import org.loose.fis.sre.exceptions.SignUpException;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;

public class AddBooksController {

    @FXML
    private Button back_button;

    @FXML
    private Button publish_button;

    @FXML
    private ChoiceBox category;

    @FXML
    private ChoiceBox condition;

    @FXML
    private TextField title_field;

    @FXML
    private TextField author_field;

    @FXML
    private TextField pag_field;

    //private String username = User.getLast_username();

   @FXML
    public void initialize() {
        category.getItems().addAll("Action", "SF", "Comedy", "Love");
        condition.getItems().addAll("Excelent", "Good", "Bad");
    }

    @FXML
    private void handleBack() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("logged-inSeller.fxml"));
        Stage window = (Stage) back_button.getScene().getWindow();
        window.setTitle("LoggedInSeller!");
        window.setScene(new Scene(root,600,400));
    }

    @FXML
    private void handlePublish() throws IOException {
       try{
           UserService.checkFilledInformationsPublishBook((String) category.getValue(),title_field.getText(),author_field.getText(),pag_field.getText(),(String) condition.getValue());
           UserService.addBook(User.getLast_username(), (String) category.getValue(),title_field.getText(),author_field.getText(),pag_field.getText(),(String) condition.getValue());

           Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
           alert.setContentText("Book published successfully!");
           alert.show();
       } catch (PublishBookException e){
           Alert alert = new Alert(Alert.AlertType.ERROR);
           alert.setContentText(e.getMessage());
           alert.show();
       }
        //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("logged-inSeller.fxml"));
        //Stage window = (Stage) back_button.getScene().getWindow();
        //window.setTitle("LoggedInSeller!");
        //window.setScene(new Scene(root,600,400));
    }

}
