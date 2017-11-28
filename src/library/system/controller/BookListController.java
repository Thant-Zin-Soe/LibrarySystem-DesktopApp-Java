/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.system.controller;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import library.system.databaseHandler.Database;
import library.system.model.Book;
import library.system.model.BookDAO;

/**
 * FXML Controller class
 *
 * @author thantzinsoe
 */
public class BookListController implements Initializable {

    @FXML
    private TableView<Book> bookTable;
    @FXML
    private TableColumn<Book, String> idColumn;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, String> authorColumn;
    @FXML
    private TableColumn<Book, String> publisherColumn;
    
    private BookDAO bookDAO;
    @FXML
    private Button deleteBtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bookDAO=new BookDAO();
        intitColumn();
        loadTableData();
    }    

    private void intitColumn() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        publisherColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));

    }
    
    
    private void loadTableData(){
       
        ObservableList<Book> bookList=bookDAO.loadBookTableData();
        if(bookList==null){
            System.out.println("Book List null");
            System.out.println("book list size"+bookList.size());
        }
        System.out.println("book list size"+bookList.size());
        bookTable.getItems().addAll(bookList);
            
    }

    @FXML
    private void deleteBook(ActionEvent event) {
        Book book=  bookTable.getSelectionModel().getSelectedItem();
        if(bookDAO.deleteBook(book.getId())){
            bookTable.getItems().clear();
            loadTableData();
            
        }
    }

   
    
}
