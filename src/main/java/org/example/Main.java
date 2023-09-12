package org.example;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args){

        try {   //Проверка доступности драйвера
            DBCreate.DBDriver();
        } catch (ClassNotFoundException e) {
            System.err.println(e);
        }

        try {   //Проверка существования Базы данных LabraryDB
            DBCreate.DBCreate();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}