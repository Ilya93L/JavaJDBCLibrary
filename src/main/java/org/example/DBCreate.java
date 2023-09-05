package org.example;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBCreate {
    final static String url = "jdbc:postgresql://localhost/?";
    final static String login = "postgres";
    final static String password ="12345";

    static void DBDriver() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
    }

    static void DBCreate() throws SQLException {
        Connection con = DriverManager.getConnection(url,login,password);
        Statement st = con.createStatement();
        int result = st.executeUpdate("CREATE DATABASE LabraryDB");
    }
}
