/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.system.databaseHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import library.system.model.Book;
import library.system.model.Member;

/**
 *
 * @author thantzinsoe
 */

public class Database {
    private static Connection conn;
    private ObservableList<Book> list;
    private static Database db;
    
    public static Database getInstance(){
    if(db==null){
        db=new Database();
        
    }
    return db;
}
    
    public Connection getConnection(){
        if(conn!=null){
            return conn;
        }
        return null;
        
    }
            
    
    
    
    
    public void connect() throws SQLException{
        try{
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Found");
            
        }
        catch(ClassNotFoundException e){
            System.out.println("Driver Not Found");
        }
        conn= DriverManager.getConnection("jdbc:mysql://192.168.64.2:3306/","thantzin","root");
    }
    public void createDatabase(){
        String sql="CREATE DATABASE IF NOT EXISTS librarysystem";
        
        try{
            Statement stat=conn.createStatement();
            stat.execute(sql);
            
           System.out.println("Success create database");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public void setupTable(){
         String sql="CREATE TABLE IF NOT EXISTS librarysystem.book (book_id varchar(20) PRIMARY KEY, bookTitle varchar(20), author varchar(200),publisher varchar(200),is_available boolean default true)";
        
        try{
            Statement stat=conn.createStatement();
            stat.execute(sql);
            
           System.out.println("Success create table");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    
     public void setupIssueTable(){
         String sql="CREATE TABLE IF NOT EXISTS librarysystem.issue (member_id varchar(20), book_id varchar(20), issue_date Date,renew_count int,foreign key (member_id) references member(member_id),foreign key (book_id) references book(book_id))";
        
        try{
            Statement stat=conn.createStatement();
            stat.execute(sql);
            
           System.out.println("Success create  issue table");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    
    public void setupMemberTable(){
         String sql="CREATE TABLE IF NOT EXISTS librarysystem.member (member_id varchar(20) PRIMARY KEY, name varchar(20), mobile varchar(200),address varchar(200))";
        
        try{
            Statement stat=conn.createStatement();
            stat.execute(sql);
            
           System.out.println("Success create member table");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

}
