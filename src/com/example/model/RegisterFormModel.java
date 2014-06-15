package com.example.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterFormModel {
    
    Connection connection;
    
    public void registerUser(String name, String surname, String email, String passwd) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("insert into TAB_USERS(NAME, SURNAME, MAIL, PASSWD) values(?,?,?,?)");
        pstmt.setString(1, name);
        pstmt.setString(2, surname);
        pstmt.setString(3, email);
        pstmt.setString(4, passwd);

        pstmt.executeUpdate();
    }
    
    public boolean loginUser(String email, String passwd) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("select count(*) from TAB_USERS where MAIL = ? and PASSWD = ?");
        pstmt.setString(1, email);
        pstmt.setString(2, passwd);
        ResultSet rs = pstmt.executeQuery();
        if(rs.getInt(1) == 1) {
            return true;
        } else {
            return false;
        }
    }
}
