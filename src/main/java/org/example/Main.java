package org.example;


import java.sql.SQLException;

public class Main {
    public static void main(String[] args){
        try {   //Проверка доступности драйвера
            DBCreate.DBDriver();
            System.out.println("Драйвер Postgresql доступен");
        } catch (ClassNotFoundException e) {
            System.err.println(e);
        }
        try {   //Проверка существования Базы данных LabraryDB
            DBCreate.DBCreate();
            System.out.println("База данных создана");
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}