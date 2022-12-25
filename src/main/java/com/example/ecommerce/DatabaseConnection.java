package com.example.ecommerce;

import java.sql.*;

public class DatabaseConnection {
// while create this DatabaseConnection write requires java.sql; in module-info.java
    Connection  con=null;
    String SQLURL="jdbc:mysql://localhost:3306/ecommerce?useSSL=false";
    String username="root";
    String password="Mister@83176";
    DatabaseConnection(){
        try {
            con= DriverManager.getConnection(SQLURL,username,password);
            if(con!=null) System.out.println("connection is established");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//    "select * from temporary";
    public  ResultSet executeQueries(String query) throws SQLException {

            Statement statement=con.createStatement();
            // this Resultset is datatype in  mysql is used to store values type like int in dsa
            ResultSet result=statement.executeQuery(query);
            return result;
    }

    public int executeUpdate(String query) throws SQLException {

        int row=0;
        Statement statement=con.createStatement();
        row=statement.executeUpdate(query);
        return row;
        //we can do with try catch also
//        int row=0;//initiation is global
//        Statement statement= null;
//        try {
//            statement = con.createStatement();
//            row=statement.executeUpdate(query);
//            return row;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return row;
    }
}
