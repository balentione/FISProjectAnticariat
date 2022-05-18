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
import org.loose.fis.sre.model.BooksWithOffer;
import org.loose.fis.sre.model.PublishedBooks;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;

public class ViewOffersController {
    @FXML
    private TableColumn<BooksWithOffer, String> author_col;

    @FXML
    private TableView<BooksWithOffer> books_table;

    @FXML
    private TableColumn<BooksWithOffer, String> category_col;

    @FXML
    private TableColumn<BooksWithOffer, String> condition_col;

    @FXML
    private TableColumn<BooksWithOffer, String> pag_col;

    @FXML
    private TableColumn<BooksWithOffer, String> title_col;

    @FXML
    private Button back_button;

    @FXML
    private Button accept_button;

    @FXML
    private Button reject_button;

    @FXML
    private Label text_label;

    private static ObjectRepository<BooksWithOffer> booksWithOfferRepository = UserService.getBooksWithOfferRepository();

    @FXML
    public void initialize() {
        text_label.setText(User.getLast_username() + "'s books offers!");
        loadData();
    }

    private void loadData(){
        category_col.setCellValueFactory(new PropertyValueFactory<>("category"));
        title_col.setCellValueFactory(new PropertyValueFactory<>("title"));
        author_col.setCellValueFactory(new PropertyValueFactory<>("author"));
        pag_col.setCellValueFactory(new PropertyValueFactory<>("number_pag"));
        condition_col.setCellValueFactory(new PropertyValueFactory<>("condition"));

        ObservableList<BooksWithOffer> booksList = FXCollections.observableArrayList();

        for(BooksWithOffer booksOffers : booksWithOfferRepository.find()){
            if(booksOffers.getUsername().equals(User.getLast_username())){
                booksList.add(booksOffers);
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

    @FXML
    private void handleAcceptSelected() throws IOException{
        for(BooksWithOffer booksOffers : books_table.getSelectionModel().getSelectedItems()){
            //vand cartea clientului
            UserService.sellBook(booksOffers.getUsername(),booksOffers.getCustomer_username(),booksOffers.getCategory(),booksOffers.getTitle(),booksOffers.getAuthor(),booksOffers.getNumber_pag(),booksOffers.getCondition());
            //o sterg din oferte
            UserService.deleteOffer(booksOffers);
        }
        books_table.getItems().removeAll(books_table.getSelectionModel().getSelectedItems());
    }

    @FXML
    private void handleRejectSelected() throws IOException{
        for(BooksWithOffer booksOffers : books_table.getSelectionModel().getSelectedItems()){
            //sterg cartea din oferte
            UserService.deleteOffer(booksOffers);
        }
        books_table.getItems().removeAll(books_table.getSelectionModel().getSelectedItems());
    }
}
