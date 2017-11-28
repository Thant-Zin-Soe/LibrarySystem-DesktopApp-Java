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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import library.system.databaseHandler.Database;
import library.system.model.Member;
import library.system.model.MemberDAO;
import library.system.util.MessageBox;

/**
 * FXML Controller class
 *
 * @author thantzinsoe
 */
public class NewMemberController implements Initializable {

    @FXML
    private TextField nameField;
    @FXML
    private TextField idField;
    @FXML
    private TextField mobileField;
    @FXML
    private TextArea addressArea;
    @FXML
    private Button addMemberBtn;
    private MemberDAO memberDAO;
    /**
     * Initializes the controller class.
     */
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    memberDAO=new MemberDAO();
    }    

    @FXML
    private void addNewMember(ActionEvent event) {
        
        String name=nameField.getText();
        String id=idField.getText();
        String mobile=mobileField.getText();
        String address=addressArea.getText();
        
        if(name.isEmpty()||id.isEmpty()||mobile.isEmpty()||address.isEmpty()){
            MessageBox.showErrorMessage("Input Error", "Please Fill all the require Fields.");
            return;
        }
        if(memberDAO.addNewMember(new Member(id,name,mobile,address))){
             MessageBox.showInformation("Success ", "Success adding Member");
           clearFields();
        }
        
    }
     private void clearFields() {
        idField.clear();
        nameField.clear();
        mobileField.clear();
        addressArea.clear();
    }
    
}
