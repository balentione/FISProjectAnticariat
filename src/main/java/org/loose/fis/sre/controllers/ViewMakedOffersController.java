package org.loose.fis.sre.controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.model.BuyedBooks;
import javafx.stage.Stage;
import org.loose.fis.sre.model.OfferedBooks;
import org.loose.fis.sre.model.PublishedBooks;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.UserService;


import java.io.IOException;

import static org.loose.fis.sre.services.UserService.offeredRepository;

public class ViewMakedOffersController {
    @FXML
    private Button accept_button;

    @FXML
    private Button reject_button;

    @FXML
    private Label text_label;

    @FXML
    private TableColumn<OfferedBooks, String> author_col;

    @FXML
    private Button back_button;

    @FXML
    private TableView<OfferedBooks> offers_table;

    @FXML
    private TableColumn<OfferedBooks, String> category_col;

    @FXML
    private TableColumn<OfferedBooks, String> condition_col;

    @FXML
    private TableColumn<OfferedBooks, String> pag_col;

    @FXML
    private TableColumn<OfferedBooks, String> title_col;

    private static ObjectRepository<OfferedBooks> offeredRepository = UserService.getOfferedRepository();

    @FXML
    public void initialize() {
        text_label.setText(User.getLast_username() + "'s offers made for the books!");
        loadData();
    }

    private void loadData(){
        category_col.setCellValueFactory(new PropertyValueFactory<>("category"));
        title_col.setCellValueFactory(new PropertyValueFactory<>("title"));
        author_col.setCellValueFactory(new PropertyValueFactory<>("author"));
        pag_col.setCellValueFactory(new PropertyValueFactory<>("number_pag"));
        condition_col.setCellValueFactory(new PropertyValueFactory<>("condition"));

        ObservableList<OfferedBooks> booksList = FXCollections.observableArrayList();

        for(OfferedBooks offeredBooks : offeredRepository.find()){
            if(offeredBooks.getUsername().equals(User.getLast_username())){
                booksList.add(offeredBooks);
            }
        }
        offers_table.setItems(booksList);
    }

    @FXML
    void handleBack(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("logged-inSeller.fxml"));
        Stage window = (Stage) back_button.getScene().getWindow();
        window.setTitle("LoggedInCustomer!");
        window.setScene(new Scene(root,600,400));
    }

    @FXML
    void handleAccept(ActionEvent event) {

    }

    @FXML
    void handleReject(ActionEvent event) {
        for(OfferedBooks offeredBooks : offers_table.getSelectionModel().getSelectedItems()){
            UserService.deleteOfferedBook(offeredBooks);
        }
        offers_table.getItems().removeAll(offers_table.getSelectionModel().getSelectedItems());
    }

}
