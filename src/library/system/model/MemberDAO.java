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
public class MemberDAO {
    public boolean addNewMember(Member member) {
        Connection conn=Database.getInstance().getConnection();
        String sql="INSERT INTO librarysystem.member (member_id,name,mobile,address) values (?,?,?,?)";
        try {
            PreparedStatement stat= conn.prepareStatement(sql);
            stat.setString(1, member.getId());
            stat.setString(2, member.getName());
            stat.setString(3, member.getMobile());
            stat.setString(4, member.getAddress());
            stat.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public ObservableList<Member> loadMemberTableData() {
                Connection conn=Database.getInstance().getConnection();

        
        ObservableList<Member> list =FXCollections.observableArrayList();
        
        try {
            String sql="SELECT * FROM librarysystem.member";
            Statement stat=conn.createStatement();
            ResultSet result= stat.executeQuery(sql);
            
            while(result.next()){
                String id=result.getString("member_id");
                String name=result.getString("name");
                String mobile=result.getString("mobile");
                String address=result.getString("address");
                
                list.add(new Member(id,name,mobile,address));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public Member searchMember(String memberID) {
       
                Connection conn=Database.getInstance().getConnection();

         Member member=null;
        try {
            String sql="SELECT * FROM librarysystem.member WHERE member_id=?";
           
            PreparedStatement stat=conn.prepareStatement(sql);
            stat.setString(1, memberID);
            ResultSet result= stat.executeQuery();
            
            while(result.next()){
                String name=result.getString("name");
                String mobile =result.getString("mobile");
                String address=result.getString("address");
                member =new Member(memberID,name,mobile,address);
            }
            return member;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return member;
    }
    
    public boolean deleteMemebr(String id) {
        Connection conn=Database.getInstance().getConnection();
        String deleteSql="delete from librarysystem.member where member_id=?";
        try {
            PreparedStatement stat=conn.prepareStatement(deleteSql);
            stat.setString(1, id);
            stat.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
