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


public class BuyedBooksController {
    @FXML
    private TableColumn<BuyedBooks, String> author_col;

    @FXML
    private Button back_button;

    @FXML
    private TableView<BuyedBooks> books_table;

    @FXML
    private TableColumn<BuyedBooks, String> category_col;

    @FXML
    private TableColumn<BuyedBooks, String> condition_col;

    @FXML
    private TableColumn<BuyedBooks, String> pag_col;

    @FXML
    private Label text_label;

    @FXML
    private TableColumn<BuyedBooks, String> title_col;

    private static ObjectRepository<BuyedBooks> buyedRepository = UserService.getBuyedRepository();

    @FXML
    public void initialize() {
        loadData();
    }

    private void loadData(){
        category_col.setCellValueFactory(new PropertyValueFactory<>("category"));
        title_col.setCellValueFactory(new PropertyValueFactory<>("title"));
        author_col.setCellValueFactory(new PropertyValueFactory<>("author"));
        pag_col.setCellValueFactory(new PropertyValueFactory<>("number_pag"));
        condition_col.setCellValueFactory(new PropertyValueFactory<>("condition"));

        ObservableList<BuyedBooks> booksList = FXCollections.observableArrayList();

        for(BuyedBooks buyedBooks : buyedRepository.find()){
            if(buyedBooks.getUsername().equals(User.getLast_username())){
                booksList.add(buyedBooks);
            }
        }
        books_table.setItems(booksList);
    }
    public void handleBack(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("logged-inCustomer.fxml"));
        Stage window = (Stage) back_button.getScene().getWindow();
        window.setTitle("LoggedInCustomer!");
        window.setScene(new Scene(root,600,400));
    }
}
