/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.system.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import library.system.databaseHandler.Database;


/**
 *
 * @author thantzinsoe
 */
public class BookDAO {
    
     public boolean addNewBook(Book book) {
         
       Connection conn=Database.getInstance().getConnection();
       String sql="INSERT INTO librarysystem.book (book_id,bookTitle,author,publisher) values (?,?,?,?)";
        try ( PreparedStatement stat= conn.prepareStatement(sql)){
           
            stat.setString(1, book.getId());
            stat.setString(2, book.getTitle());
            stat.setString(3, book.getAuthor());
            stat.setString(4, book.getPublisher());
            stat.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
     
      public boolean checkBookAvailable(String id) {
           Connection conn=Database.getInstance().getConnection();
        String sql="select * from librarysystem.book where book_id=?";
        boolean isAvailable=false;
        try ( PreparedStatement stmt=conn.prepareStatement(sql)){    
           
            stmt.setString(1, id);
         ResultSet result=   stmt.executeQuery();
            
            while(result.next()){
                isAvailable=result.getBoolean("is_available");
            }
            return isAvailable;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return isAvailable;
    }
      
       public boolean deleteBook(String id) {
            Connection conn=Database.getInstance().getConnection();
        String deleteSql="delete from librarysystem.book where book_id=?";
        try( PreparedStatement stat=conn.prepareStatement(deleteSql)) {
           
            stat.setString(1, id);
            stat.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
       
       public ObservableList<Book> loadBookTableData(){
        Connection conn=Database.getInstance().getConnection();
        ObservableList<Book> list =FXCollections.observableArrayList();
        
        try {
            String sql="SELECT * FROM librarysystem.book";
            Statement stat=conn.createStatement();
            ResultSet result= stat.executeQuery(sql);
            
            while(result.next()){
                String id=result.getString("book_id");
                String title=result.getString("bookTitle");
                String author=result.getString("author");
                String publisher=result.getString("publisher");
                
                list.add(new Book(title,id,author,publisher));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
       
       public Book searchBook(String bookId){
       Connection conn=Database.getInstance().getConnection(); 
         Book book=null;
        try {
            String sql="SELECT * FROM librarysystem.book WHERE book_id=?";
           
            PreparedStatement stat=conn.prepareStatement(sql);
            stat.setString(1, bookId);
            ResultSet result= stat.executeQuery();
            
            while(result.next()){
                String title=result.getString("bookTitle");
                String author =result.getString("author");
                String publisher=result.getString("publisher");
                book =new Book(title,bookId,author,publisher);
            }
            return book;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return book;
    }
       
       public boolean issueBook(String bookId){
            Connection conn=Database.getInstance().getConnection(); 
            String updateSql="update librarysystem.book set is_available=false  where book_id=?";
            
              PreparedStatement preStat2;
         try {
             preStat2 = conn.prepareStatement(updateSql);
              preStat2.setString(1, bookId);
            preStat2.execute();
            return true;
         } catch (SQLException ex) {
             Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
           return false;
       }
       
       public boolean submitBook(String bookID){
           Connection conn=Database.getInstance().getConnection();
            String updateSql="update librarysystem.book set is_available=true where book_id=?";
            
            PreparedStatement stat2;
         try {
             stat2 = conn.prepareStatement(updateSql);
              stat2.setString(1, bookID);
            stat2.execute();
            
           
            return true;
        
         } catch (SQLException ex) {
             Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
  
        return false;
       }

}
