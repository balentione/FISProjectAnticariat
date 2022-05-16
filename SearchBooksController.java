package org.loose.fis.sre.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.model.PublishedBooks;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;
import java.util.Locale;

public class SearchBooksController {
    @FXML
    private TableColumn<PublishedBooks, String> author_col;

    @FXML
    private TableView<PublishedBooks> books_table;

    @FXML
    private TableColumn<PublishedBooks, String> category_col;

    @FXML
    private TableColumn<PublishedBooks, String> condition_col;

    @FXML
    private TableColumn<PublishedBooks, String> pag_col;

    @FXML
    private TableColumn<PublishedBooks, String> title_col;

    @FXML
    private Button back_button;

    @FXML
    private TextField titleField;

    private static ObjectRepository<PublishedBooks> booksRepository = UserService.getBooksRepository();

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

        ObservableList<PublishedBooks> booksList = FXCollections.observableArrayList();

        for(PublishedBooks publishedBooks : booksRepository.find()){
                booksList.add(publishedBooks);
        }
        books_table.setItems(booksList);

        FilteredList<PublishedBooks> filteredData = new FilteredList<>(booksList, b -> true);
        titleField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(publishedBooks ->{
                if(newValue.isEmpty() || newValue.isBlank() || newValue==null){
                    return true;
                }
                String searchKeyword = newValue.toLowerCase();

                if(publishedBooks.getTitle().toLowerCase().indexOf(searchKeyword) > -1){
                    return true;
                } else{
                    return false;
                }
            });
        });

        SortedList<PublishedBooks> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(books_table.comparatorProperty());
        books_table.setItems(sortedData);
    }

    @FXML
    private void handleBack() throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("logged-inCustomer.fxml"));
        Stage window = (Stage) back_button.getScene().getWindow();
        window.setTitle("LoggedInCustomer!");
        window.setScene(new Scene(root,600,400));
    }

    //de modificat
    @FXML
    private void handleBuySelected() throws IOException{
        for(PublishedBooks publishedBooks : books_table.getSelectionModel().getSelectedItems()){
            UserService.deleteBook(publishedBooks);
        }
        books_table.getItems().removeAll(books_table.getSelectionModel().getSelectedItems());
    }
}
