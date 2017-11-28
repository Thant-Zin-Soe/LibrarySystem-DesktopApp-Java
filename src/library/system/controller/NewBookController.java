/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.system.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import library.system.databaseHandler.Database;
import library.system.model.Book;
import library.system.model.BookDAO;
import library.system.util.MessageBox;

/**
 * FXML Controller class
 *
 * @author thantzinsoe
 */
public class NewBookController implements Initializable {

    @FXML
    private TextField bookTitleField;
    @FXML
    private TextField bookIdField;
    @FXML
    private TextField authorField;
    @FXML
    private TextField publisherField;
    @FXML
    private Button addBookBtn;

    /**
     * Initializes the controller class.
     */
    
    private BookDAO bookDAO; 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bookDAO=new BookDAO();
    }    

    @FXML
    private void addBook(ActionEvent event) {
        
       String title=bookTitleField.getText();
       String id=bookIdField.getText();
       String author=authorField.getText();
       String publisher=publisherField.getText();
       
       if(title.isEmpty()||id.isEmpty()||author.isEmpty()||publisher.isEmpty()){
          MessageBox.showErrorMessage("Inpur Error", "Please fill all fields.");
          return;
       }
       
       if(bookDAO.addNewBook(new Book(title,id,author,publisher))){
           MessageBox.showInformation("Success ", "Success adding Book");
           clearFields();
       }
    
         
    }

    private void clearFields() {
        bookTitleField.clear();
        bookIdField.clear();
        authorField.clear();
        publisherField.clear();
    }
    
}
