/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.system.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import library.system.databaseHandler.Database;

/**
 *
 * @author thantzinsoe
 */
public class IssueDAO {
    public boolean reNew(String bookID) { 
        Connection conn=Database.getInstance().getConnection();
 String updateSql="update librarysystem.issue set issue_date=CURDATE() ,renew_count=renew_count+1 where book_id=?";
        
        PreparedStatement stat;
        try {
            stat = conn.prepareStatement(updateSql);
            stat.setString(1, bookID);
            stat.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false; 
    }
    
    public Issue searchIssueBook(String bookId) {
        
        Connection conn=Database.getInstance().getConnection();
        String sql="select * from librarysystem.issue where book_id=?";
        ResultSet result=null;
        Issue issue=null;
        try {
            PreparedStatement stat=conn.prepareStatement(sql);
            stat.setString(1, bookId);
           result=  stat.executeQuery();
           if(result.next()){
               String memberID=result.getString("member_id");
               String bookID=result.getString("book_id");
               Date date=result.getDate("issue_date");
               int renewCount=result.getInt("renew_count");
                issue=new Issue(memberID,bookID,date,renewCount);
           }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return issue;
    }
    
    public boolean issueBook(String bookId,String memberId){
        Connection conn=Database.getInstance().getConnection();
       String insertSql="insert into librarysystem.issue (member_id,book_id,issue_date,renew_count) value(?,?,CURDATE(),0)";
       
        PreparedStatement preStat;
        try {
            preStat = conn.prepareStatement(insertSql);
             preStat.setString(1, memberId);
            preStat.setString(2,bookId);
            preStat.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(IssueDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
           return false;
    }
    public boolean submitBook(String bookID){
        Connection conn=Database.getInstance().getConnection();
         String deleteSql="delete from librarysystem.issue where book_id=?";
           
            
            PreparedStatement stat1;            
        try {
            stat1 = conn.prepareStatement(deleteSql);
             stat1.setString(1, bookID);           
            stat1.execute();           
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(IssueDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
           
       
        return false;
    }

}
