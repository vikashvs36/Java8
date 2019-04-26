package com.dao;


import java.sql.*;

// Create DbConnection Object to create connection.
public class DbConnection {

    // Create connection object.
    static Connection connection=null;

    // Create Connection method.
    public static Connection getConnection(){

        // Create Url.
        String url="jdbc:mysql://localhost:3306/vsmart";

        try{

            // Create Object of Mysql jdbc Driver class.
            Class.forName("com.mysql.jdbc.Driver");
            // Get Connection from DriverManager Object.
            connection= DriverManager.getConnection(url, "root", "root");
        }
        catch (Exception e){ System.out.println("Error : "+e.getMessage()); }

        // Return connection.
        return connection;
    }



    // Create Main method to test 'getConnection()' method.
    public static void main(String[] args) {
        getConnection();
    }

}
