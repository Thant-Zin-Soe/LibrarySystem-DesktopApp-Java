/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.system.util;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;


/**
 *
 * @author thantzinsoe
 */
public class MessageBox {
    public static void showErrorMessage(String title,String message){
        Alert alert=new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
    }
    
   public static void showInformation(String title,String message){
       Alert alert=new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.show();
   }
   
   public static Optional<ButtonType> showConfirmMessage(String title, String message){
       Alert alert=new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        Optional<ButtonType> choose= alert.showAndWait();
        return choose;
   }
}
 