/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.system.model;

import java.sql.Date;

/**
 *
 * @author thantzinsoe
 */
public class Issue {
    private String memberID;
    private String bookID;
    private Date issueDate;
    private int renewCount;

    public Issue(String memberID, String bookID, Date issueDate, int renewCount) {
        this.memberID = memberID;
        this.bookID = bookID;
        this.issueDate = issueDate;
        this.renewCount = renewCount;
    }
    

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public int getRenewCount() {
        return renewCount;
    }

    public void setRenewCount(int renewCount) {
        this.renewCount = renewCount;
    }

}
