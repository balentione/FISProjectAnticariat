package org.loose.fis.sre.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.model.SelledBooks;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;

public class SelledBooksController {
    @FXML
    private TableColumn<SelledBooks, String> author_col;

    @FXML
    private TableView<SelledBooks> books_table;

    @FXML
    private TableColumn<SelledBooks, String> category_col;

    @FXML
    private TableColumn<SelledBooks, String> condition_col;

    @FXML
    private TableColumn<SelledBooks, String> pag_col;

    @FXML
    private TableColumn<SelledBooks, String> title_col;

    @FXML
    private Button back_button;

    @FXML
    private Label text_label;

    private static ObjectRepository<SelledBooks> selledBooksRepository = UserService.getSelledBooksRepository();

    @FXML
    public void initialize() {
        text_label.setText(User.getLast_username() + "'s selled books!");
        loadData();
    }

    private void loadData(){
        category_col.setCellValueFactory(new PropertyValueFactory<>("category"));
        title_col.setCellValueFactory(new PropertyValueFactory<>("title"));
        author_col.setCellValueFactory(new PropertyValueFactory<>("author"));
        pag_col.setCellValueFactory(new PropertyValueFactory<>("number_pag"));
        condition_col.setCellValueFactory(new PropertyValueFactory<>("condition"));

        ObservableList<SelledBooks> booksList = FXCollections.observableArrayList();

        for(SelledBooks selledBooks : selledBooksRepository.find()){
            if(selledBooks.getUsername().equals(User.getLast_username())){
                booksList.add(selledBooks);
            }
        }
        books_table.setItems(booksList);
    }

    @FXML
    private void handleBack() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("logged-inSeller.fxml"));
        Stage window = (Stage) back_button.getScene().getWindow();
        window.setTitle("LoggedInSeller!");
        window.setScene(new Scene(root,600,400));
    }
}
