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
            if (DBCreate.DBCreate()!=0)
            {
               System.err.println("Ошибка!Завершение работы программы");
                return;
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}