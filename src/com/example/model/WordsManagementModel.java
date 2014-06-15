package com.example.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.example.ExampleUI;
import com.vaadin.data.Item;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.Notification;

public class WordsManagementModel {
        
        
        private Object[] colList = new Object[] {"ID", "POL", "ANG", "LIST_ID"};
        
        private Connection conn;
        private IndexedContainer wordsContainer;
        
        
        public WordsManagementModel(){
            try {
                conn = DriverManager.getConnection(ExampleUI.DB_URL);
            } catch (SQLException e) {
                Notification.show("Problem with connection to database!");
                e.printStackTrace();
                System.exit(-1);
            }                dataToContainer();
                                
        }
        
/*
 * TODO: for now there's every word on the list (should be chosen according to list - i think so)+
 * number on the list instead of list name
 */

        public void dataToContainer(){
                setWordsContainer(new IndexedContainer());
                Statement stmt = null;
                try{
                        getWordsContainer().addContainerProperty("ID", Integer.class,0);
                        getWordsContainer().addContainerProperty("POL", String.class, "");
                        getWordsContainer().addContainerProperty("ANG",String.class,"");
                        getWordsContainer().addContainerProperty("LIST_ID", Integer.class, 0);
                        
                        stmt = getConn().createStatement();
                        String sql = "SELECT * FROM TAB_WORDS";
                        ResultSet rs = stmt.executeQuery(sql);
                        
                        
                        while(rs.next()){
                                Object id = getWordsContainer().addItem();
                                
                                getWordsContainer().getContainerProperty(id, "ID").setValue(rs.getInt("ID"));
                                getWordsContainer().getContainerProperty(id, "POL").setValue(rs.getString("POL"));
                                getWordsContainer().getContainerProperty(id, "ANG").setValue(rs.getString("ANG"));
                                getWordsContainer().getContainerProperty(id, "LIST_ID").setValue(rs.getInt("LIST_ID"));
                                
                        }
                        
                        rs.close();
                        stmt.close();
                }
                catch(Exception e){
                        System.err.println(e.getClass().getName() + ": " + e.getMessage());
                        System.exit(0);
                }
                System.out.println("Base was loaded to container");
        }
        
        public int insertRecord(){
                Statement stmt = null;
                try{
                        String sql;
                        int id;
                        //getConn().setAutoCommit(false);
                        stmt = getConn().createStatement();
                        sql = "SELECT MAX(ID)+1 AS i FROM TAB_WORDS;";
                        ResultSet rs = stmt.executeQuery(sql);
                        id = rs.getInt("i");
                        System.out.println(id);
                        sql = "INSERT INTO TAB_WORDS(ID,POL,ANG,LIST_ID)" + "VALUES(?,?,?,?)";
                        PreparedStatement pstmt = getConn().prepareStatement(sql);
                        
                        pstmt.setInt(1, id);
                        pstmt.setString(2, "New POL");
                        pstmt.setString(3, "New ANG");
                        pstmt.setInt(4, 0);
                        pstmt.addBatch();
                
                        int[] updateCounts = pstmt.executeBatch();
                        pstmt.close();
                        //getConn().commit();
                        //getConn().setAutoCommit(true);
                        System.out.println(updateCounts);
                        addItemToContainer(id);
                        System.out.println("Record was inserted successfully");
                        return updateCounts[0];
                        
                        
                }
                catch(Exception e){
                        System.err.println(e.getClass().getName() + ": " + e.getMessage());
                        return 0;
                }
        }
        
        
        public int updateRecord(Object i, String nat, String tran, int l_id){
                int[] updateCounts;
                try{
                        int id;
                        Item itemToUpdate = getWordsContainer().getItem(i);
                //      getConn().setAutoCommit(false);
                        id = Integer.parseInt(itemToUpdate.getItemProperty("ID").getValue().toString());
                        
                        String sql = "UPDATE TAB_WORDS set POL=?, ANG=?, LIST_ID=? WHERE ID=?";
                //      getConn().setAutoCommit(false);
                                                                        
                        PreparedStatement pstmt = getConn().prepareStatement(sql);
                        pstmt.setString(1, nat);
                        pstmt.setString(2, tran);
                        pstmt.setInt(3, l_id);
                        pstmt.setInt(4, id);
                        pstmt.addBatch();
                        
                        updateCounts = pstmt.executeBatch();
                        pstmt.close();
                ////    getConn().commit();
                //      getConn().setAutoCommit(true);
                        
                        System.out.println("Updating done successfully");
                        return updateCounts[0];         
                        //return 1;
                }
                catch(Exception e){
                        System.err.println(e.getClass().getName() + ": " + e.getMessage());
                        System.exit(0);
                        return 0;
                }
        }

        
        
        public boolean deleteRecord(Object i){
                try{
                        int id;
                        Item itemToDelete = getWordsContainer().getItem(i);
                        String sql = "DELETE from TAB_WORDS WHERE ID=?";
                //      getConn().setAutoCommit(false);
                        id = Integer.parseInt(itemToDelete.getItemProperty("ID").getValue().toString());
                        PreparedStatement pstmt = getConn().prepareStatement(sql);
                        pstmt.setInt(1, id);
                        pstmt.addBatch();
                        
                        int[] updateCounts = pstmt.executeBatch();
                        pstmt.close();
                //      getConn().commit();
                //      getConn().setAutoCommit(true);
                        getWordsContainer().removeItem(i);
                        System.out.println("Recold was deleted successfully");
                        return true;
                }catch(Exception e){
                        System.err.println(e.getClass().getName() + ": " + e.getMessage());
                        System.exit(0);
                        return false;
                }
        }
        
        public void selectRecords(){
                Statement stmt = null;
                try {
                        stmt = getConn().createStatement();
                        String  sql = "SELECT * FROM TAB_WORDS";
                        ResultSet rs = stmt.executeQuery(sql);
                        while(rs.next()){
                                int id = rs.getInt("ID");
                                String nat = rs.getString("POL");
                                String trans = rs.getString("ANG");
                                int l_id = rs.getInt("LIST_ID");
                                
                                System.out.println("ID = " + id);
                                System.out.println("POL = " + nat);
                                System.out.println("ANG = " + trans);
                                System.out.println("LIST_ID = " + l_id);
                                
                        }
                        rs.close();
                        stmt.close();
                }
                catch(Exception e){
                        System.err.println(e.getClass().getName() + ": " + e.getMessage());
                        System.exit(0);
                }
                System.out.println("Select operation done successfully");
        }
        
        
        public void addItemToContainer(int in_id){
                try{
                        Object id = getWordsContainer().addItemAt(0);
                        
                        getWordsContainer().getContainerProperty(id, "ID").setValue(in_id);
                        getWordsContainer().getContainerProperty(id, "POL").setValue("POL word");
                        getWordsContainer().getContainerProperty(id,"ANG").setValue("ANG word");
                        getWordsContainer().getContainerProperty(id, "LIST_ID").setValue(0);
                        
                        System.out.println("Record was added to container");
                        
                }
                catch(Exception e){
                        System.err.println(e.getClass().getName() + ": " + e.getMessage());
                }
        }

        
        public void deleteTable() {
                Statement stmt = null;
                try {
                        stmt = getConn().createStatement();
                        String sql = "DROP TABLE TAB_WORDS ";
                        stmt.executeUpdate(sql);
                } catch (SQLException se) {
                        se.printStackTrace();
                }
                System.out.println("Table was deleted");
        }
        
        public void createTable() {
                Statement stmt = null;
                try {
                        stmt = getConn().createStatement();
                        String sql = "CREATE TABLE TAB_WORDS" + "(ID INTEGER NOT NULL,"
                                        + " POL VARCHAR(255) NOT NULL, "
                                        + " ANG VARCHAR(255) NOT NULL, "
                                        + " LIST_ID INTEGER, " + "PRIMARY KEY (ID))";
                        stmt.executeUpdate(sql);
                        stmt.close();
                } catch (Exception e) {
                        System.err.println(e.getClass().getName() + ": " + e.getMessage());
                        System.exit(0);
                }
                System.out.println("Table was created successfully");

        }
                
        
        private Connection getConn() {
                return conn;
        }


        private void setConn(Connection conn) {
                this.conn = conn;
        }

        private void setWordsContainer(IndexedContainer wordsContainer) {
                this.wordsContainer = wordsContainer;
        }
        
        public IndexedContainer getWordsContainer(){
                return wordsContainer;
        }
        
        public Object[] getColList(){
                return colList;
        }
        

}
