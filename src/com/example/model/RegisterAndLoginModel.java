package com.example.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.example.ExampleUI;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Notification;

public class RegisterAndLoginModel {

    Connection connection;


    public boolean registerUser(String name, String surname, String email,
            String passwd) throws SQLException {
        initConnection();
        PreparedStatement stmt = connection.prepareStatement("select count(*) from TAB_USERS where MAIL = ? ");
        stmt.setString(1, email);
        ResultSet rs = stmt.executeQuery();
        if(rs.getInt(1) > 0) {
           return false;
        }
                 PreparedStatement pstmt = connection.prepareStatement(
                        "insert into TAB_USERS(NAME, SURNAME, MAIL, PASSWD) values(?,?,?,?)",
                        Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, name);
        pstmt.setString(2, surname);
        pstmt.setString(3, email);
        pstmt.setString(4, passwd);

        pstmt.executeUpdate();
        connection.close();
        return true;
        
    }

    public String loginUser(String email, String passwd) throws SQLException {
       initConnection();
       PreparedStatement pstmt = connection
                .prepareStatement("select count(*), ID from TAB_USERS where MAIL = ? and PASSWD = ?");
        pstmt.setString(1, email);
        pstmt.setString(2, passwd);
        ResultSet rs = pstmt.executeQuery();
        if (rs.getInt(1) == 1) {
            VaadinSession.getCurrent().setAttribute(ExampleUI.LOGGED_USER, rs.getInt(2));
            Statement stmt = connection.createStatement();
            ResultSet rs2 = stmt.executeQuery("select NAME || ' ' || SURNAME from TAB_USERS where ID = " + rs.getInt(2));
            return rs2.getString(1);
        } else {
            System.out.println(rs.getInt(1));
            return "";
        }
    }
    
    private void initConnection() {
        String driver = "org.sqlite.JDBC";
        Connection conn;
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            Notification.show("JDBC Driver Not Found");
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(ExampleUI.DB_URL);
        } catch (SQLException e) {
            Notification.show("Problem with connection to database!");
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
