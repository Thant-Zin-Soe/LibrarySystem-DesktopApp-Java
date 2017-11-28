/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.system.controller;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import library.system.databaseHandler.Database;
import library.system.model.Book;
import library.system.model.BookDAO;
import library.system.model.Issue;
import library.system.model.IssueDAO;
import library.system.model.Member;
import library.system.model.MemberDAO;
import library.system.util.MessageBox;

/**
 *
 * @author thantzinsoe
 */
public class MainController implements Initializable {

    @FXML
    private Button homeBtn;
    @FXML
    private Button newBookBtn;
    @FXML
    private StackPane centerPane;
    @FXML
    private TabPane homeView;
    @FXML
    private Button newMember;
    @FXML
    private Button bookBtn;
    @FXML
    private Button memberBtn;
    @FXML
    private JFXTextField bookId;
    @FXML
    private Text titleField;
    @FXML
    private Text authorField;
    @FXML
    private Text publisherField;

    private Database db;
    @FXML
    private JFXTextField memberId;
    @FXML
    private Text nameField;
    @FXML
    private Text mobileField;
    @FXML
    private Text addressField;
    @FXML
    private Button issueBtn;
    @FXML
    private Text availableLabel;

    private boolean searchBookInfo = false;
    private boolean searchMemberInfo = false;
    @FXML
    private TextField searchBookIdField;
    @FXML
    private ListView<String> bookList;
    @FXML
    private Button renewBtn;
    @FXML
    private Button submitBtn;
    @FXML
    private MenuItem aboutMenu;
    
    
    private final Image homeImg=new Image("/library/system/icons/home.png");
    private final Image homeGrayImg=new Image("/library/system/icons/home-gray.png");
    private final Image newBookImg=new Image("/library/system/icons/addbook.png");
    private final Image newBookGrayImg=new Image("/library/system/icons/addbook-gray.png");
    private final Image newemberImg=new Image("/library/system/icons/addmember.png");
    private final Image newMemberGrayImg=new Image("/library/system/icons/addmember-gray.png");
    private final Image booksImg=new Image("/library/system/icons/books.png");
    private final Image booksGrayImg=new Image("/library/system/icons/books-gray.png");
    private final Image membersImg=new Image("/library/system/icons/listmembers.png");
    private final Image membersGrayImg=new Image("/library/system/icons/listmembers-gray.png");
    
    @FXML
    private ImageView homeImgView;
    @FXML
    private ImageView newBookImgView;
    @FXML
    private ImageView newMemberView;
    @FXML
    private ImageView bookImgView;
    @FXML
    private ImageView memberImgView;
    
    private BookDAO bookDAO;
    private MemberDAO memberDAO;
    private IssueDAO issueDAO;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = Database.getInstance();
        bookDAO=new BookDAO();
        memberDAO=new MemberDAO();
        issueDAO=new IssueDAO();
        homeBtn.setStyle(activeStyle);
       
    }

    @FXML
    private void loadHomeView(ActionEvent event) {
        centerPane.getChildren().clear();
        centerPane.getChildren().add(homeView);
        activeHome();
    }

    @FXML
    private void loadNewBookView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/library/system/view/newbook.fxml"));
        centerPane.getChildren().clear();
        centerPane.getChildren().add(root);
        activeNewBook();
    }

    @FXML
    private void loadNewMemberView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/library/system/view/newMember.fxml"));
        centerPane.getChildren().clear();
        centerPane.getChildren().add(root);
        activeNewMember();
    }

    @FXML
    private void loadBookView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/library/system/view/bookList.fxml"));
        centerPane.getChildren().clear();
        centerPane.getChildren().add(root);
        activeBooks();
    }

    @FXML
    private void loadMemberView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/library/system/view/memberList.fxml"));
        centerPane.getChildren().clear();
        centerPane.getChildren().add(root);
        activeMembers();
    }

    @FXML
    private void searchBookInfo(ActionEvent event) {
        
        searchBookInfo = false;
        
        clearBookInfo();
        
        String bookID = bookId.getText();
        if (bookID.isEmpty()) {
            MessageBox.showErrorMessage("Error", "Please enter bookID first");
            return;
        }

        Book book = bookDAO.searchBook(bookID);
        
        
        if(book!=null){
            
        String title = book.getTitle();
        String author = book.getAuthor();
        String publisher = book.getPublisher();

        titleField.setText(title);
        authorField.setText(author);
        publisherField.setText(publisher);

        if (bookDAO.checkBookAvailable(bookID)) {
            availableLabel.setText("Available");
        } else {
            availableLabel.setText("Not Availabel");
        }
        
        searchBookInfo = true;
       
        
        }

    }

    @FXML
    private void searchMemberInfo(ActionEvent event) {
        searchMemberInfo = false;
        clearMembrInfo();
        
        String memberID = memberId.getText();
        if (memberID.isEmpty()) {
            MessageBox.showErrorMessage("Error", "Please enter memberID first");
            return;
        }
        Member member = memberDAO.searchMember(memberID);
        
        if(member!=null){
        String name = member.getName();
        String mobile = member.getMobile();
        String address = member.getAddress();

        nameField.setText(name);
        mobileField.setText(mobile);
        addressField.setText(address);
        
        searchMemberInfo=true;
        }
    }

    @FXML
    private void issueBook(ActionEvent event) {
        
        System.out.println(searchBookInfo);

        String bookID = bookId.getText();
        String memberID = memberId.getText();

        if (bookID.isEmpty()) {
            MessageBox.showErrorMessage("Error", "Please fill Book ID  Field");
            return;
        }

        if (memberID.isEmpty()) {
            MessageBox.showErrorMessage("Error", "Please fill Member ID  Field");
            return;
        }

        if (!searchBookInfo) {
            MessageBox.showErrorMessage("Error", "Book does not exit");
            return;
        }

        if (!searchMemberInfo) {
            MessageBox.showErrorMessage("Error", "Member does not exit");
            return;
        }

        Optional<ButtonType> choose = MessageBox.showConfirmMessage("Confirmation", "Are you sure to issue this book.");

        if (choose.get() == ButtonType.OK) {

            if (bookDAO.checkBookAvailable(bookID)) {

                if (issueDAO.issueBook(bookID, memberID)&&bookDAO.issueBook(bookID)) {
                    MessageBox.showInformation("Success", "Can issue Book.");
                }

            } else {
                MessageBox.showErrorMessage("Error", "This book is already issued.");
            }
        }

    }

    private void clearBookInfo() {
        titleField.setText("_");
        authorField.setText("_");
        publisherField.setText("_");
        availableLabel.setText("_");
    }

    private void clearMembrInfo() {
        nameField.setText("_");
        mobileField.setText("_");
        addressField.setText("_");
    }

    @FXML
    private void loadIssueInfo(ActionEvent event) {
        bookList.getItems().clear();
        
        ObservableList<String> listData=FXCollections.observableArrayList();
        
        
        String bookId=searchBookIdField.getText();
        
        if(bookId.isEmpty()){
        MessageBox.showErrorMessage("Error", "Please enter Book_ID first.");
        }
        
        Issue issue=issueDAO.searchIssueBook(bookId);
        
            if(issue!=null){
                
                    String memberID=issue.getMemberID();
                    String bookID=issue.getBookID();
                    Date issueDate=issue.getIssueDate();
                    int count=issue.getRenewCount();
                    
                     listData.add("Issue Date: "+issueDate+"\tCount: "+count);
                    
                    
                    listData.add("Book Info");
                    Book book=bookDAO.searchBook(bookId);
                    listData.add("\tTitle: "+book.getTitle());
                    listData.add("\tAuthor: "+book.getAuthor());
                    listData.add("\tPublisher: "+book.getPublisher());
                    
                    listData.add("Member Info ");
                    Member member=memberDAO.searchMember(memberID);
                    listData.add("\tName: "+member.getName());
                    listData.add("\tMobile: "+member.getMobile());
                    listData.add("\tAddress: "+member.getAddress());
                    
                     
                   
                    
                    
                }
            
        
        
        bookList.getItems().addAll(listData);
    }

    @FXML
    private void renewBook(ActionEvent event) {
        String bookID=searchBookIdField.getText();
        
        if(bookID.isEmpty()){
            MessageBox.showErrorMessage("Error", "Please Enter BookiD first.");
            return;
        }
         Optional<ButtonType> choose = MessageBox.showConfirmMessage("Confirmation", "Are you sure to reNew this book.");

        if (choose.get() == ButtonType.OK) {
        if(issueDAO.reNew(bookID)){
            MessageBox.showInformation("Success", "Renew book success");
        }
        }
    }

    @FXML
    private void submitBook(ActionEvent event) {
        String  bookID=searchBookIdField.getText();
        
        if(bookID.isEmpty()){
            MessageBox.showErrorMessage("Error", "Please Enter BookiD first.");
            return;
        }
         Optional<ButtonType> choose = MessageBox.showConfirmMessage("Confirmation", "Are you sure to return this book.");

        if (choose.get() == ButtonType.OK) {
        
        if(issueDAO.submitBook(bookID)&&bookDAO.submitBook(bookID)){
            MessageBox.showInformation("Success", "Submit book success");
        }
        }
        
    }
    
    private final String activeStyle="-fx-text-fill:#ffff8d";
    private final String defaultStyle="-fx-text-fill:#b2b2b2";
    
    

    private void activeHome() {
      homeBtn.setStyle(activeStyle);
      bookBtn.setStyle(defaultStyle);
      newBookBtn.setStyle(defaultStyle);
      newBookBtn.setStyle(defaultStyle);
      newMember.setStyle(defaultStyle);
      

      homeImgView.setImage(homeImg);
      newBookImgView.setImage(newBookGrayImg);
      newMemberView.setImage(newMemberGrayImg);
      bookImgView.setImage(booksGrayImg);
      memberImgView.setImage(membersGrayImg);
     

    }

    private void activeNewBook() {
       homeBtn.setStyle(defaultStyle);
       newBookBtn.setStyle(activeStyle);
       newMember.setStyle(defaultStyle);
       bookBtn.setStyle(defaultStyle);
       memberBtn.setStyle(defaultStyle);
        
      homeImgView.setImage(homeGrayImg);
      newBookImgView.setImage(newBookImg);
      newMemberView.setImage(newMemberGrayImg);
      bookImgView.setImage(booksGrayImg);
      memberImgView.setImage(membersGrayImg);
    }

    private void activeNewMember() {
        
       homeBtn.setStyle(defaultStyle);
       newBookBtn.setStyle(defaultStyle);
       newMember.setStyle(activeStyle);
       bookBtn.setStyle(defaultStyle);
       memberBtn.setStyle(defaultStyle);
     
      homeImgView.setImage(homeGrayImg);
      newBookImgView.setImage(newBookGrayImg);
      newMemberView.setImage(newemberImg);
      bookImgView.setImage(booksGrayImg);
      memberImgView.setImage(membersGrayImg);
    }

    private void activeBooks() {
      homeBtn.setStyle(defaultStyle);
       newBookBtn.setStyle(defaultStyle);
       newMember.setStyle(defaultStyle);
       bookBtn.setStyle(activeStyle);
       memberBtn.setStyle(defaultStyle);
        
        homeImgView.setImage(homeGrayImg);
      newBookImgView.setImage(newBookGrayImg);
      newMemberView.setImage(newMemberGrayImg);
      bookImgView.setImage(booksImg);
      memberImgView.setImage(membersGrayImg);
    }

    private void activeMembers() {
        homeBtn.setStyle(defaultStyle);
       newBookBtn.setStyle(defaultStyle);
       newMember.setStyle(defaultStyle);
       bookBtn.setStyle(defaultStyle);
       memberBtn.setStyle(activeStyle);
        
        homeImgView.setImage(homeGrayImg);
      newBookImgView.setImage(newBookGrayImg);
      newMemberView.setImage(newMemberGrayImg);
      bookImgView.setImage(booksGrayImg);
      memberImgView.setImage(membersImg);
    }
    
   

    @FXML
    private void loadAboutView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/library/system/view/aboutMenu.fxml"));
        Stage stage=new Stage();
        Scene scene=new Scene(root);
        stage.setScene(scene);
        
        stage.initOwner(homeBtn.getScene().getWindow());
        stage.initModality(Modality.APPLICATION_MODAL);
        
        stage.show();
    }

}
