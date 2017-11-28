/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.system;

import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import library.system.databaseHandler.Database;

/**
 *
 * @author thantzinsoe
 */
public class LibrarySystem extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Database db=new Database();
        
        
        try{
            db.connect();
            System.out.println("Connection success");
        }
        catch(SQLException e){
            System.out.println("Cannot Connect");
            e.printStackTrace();
        }
        db.createDatabase();
        db.setupTable();
        db.setupMemberTable();
        db.setupIssueTable();
        
        
        Parent root = FXMLLoader.load(getClass().getResource("/library/system/view/main.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
