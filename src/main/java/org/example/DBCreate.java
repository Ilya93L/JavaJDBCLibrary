package org.example;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBCreate {
    final static String url = "jdbc:postgresql://localhost/?";
    final static String login = "postgres";
    final static String password ="12345";
    private final static String sql_tab = "create table table_author\n" +
            "(\n" +
            "\tid_author SERIAL PRIMARY KEY, \n" +
            "\tauthor_name CHARACTER VARYING(50) not null,\n" +
            "\tauthor_surname CHARACTER VARYING(50) not null,\n" +
            "\tauthor_patronymic CHARACTER VARYING(50) not null\n" +
            ");\n" +
            "\n" +
            "create table table_genre\n" +
            "(\n" +
            "\tid_genre SERIAL PRIMARY KEY, \n" +
            "\ttitle_genre CHARACTER VARYING(50) not null\n" +
            ");\n" +
            "\n" +
            "create table table_book\n" +
            "(\n" +
            "\tid_book SERIAL PRIMARY KEY, \n" +
            "\ttitle_book CHARACTER VARYING(100) NOT null,\n" +
            "\tid_genre INTEGER REFERENCES table_genre(id_genre) NOT null,\n" +
            "\tid_author INTEGER REFERENCES table_author(id_author) NOT null\n" +
            ");\n" +
            "\n" +
            "CREATE TABLE table_role\n" +
            "(\n" +
            "\tid_role SERIAL PRIMARY KEY,\n" +
            "\trole CHARACTER VARYING(30)\n" +
            ");\n" +
            "\n" +
            "CREATE TABLE table_user\n" +
            "(\n" +
            "\tid_user SERIAL PRIMARY KEY,\n" +
            "\tuser_name CHARACTER VARYING(50)NOT NULL,\n" +
            "\tuser_surname CHARACTER VARYING(50)NOT NULL,\n" +
            "\tuser_patronymic CHARACTER VARYING(50)NOT NULL,\n" +
            "\tuser_telephone CHARACTER VARYING(50)NOT NULL,\n" +
            "\tuser_address CHARACTER VARYING(50)NOT NULL,\n" +
            "\tuser_birthday DATE NOT NULL,\n" +
            "\tuser_login CHARACTER VARYING(50) NOT NULL,\n" +
            "\tuser_password CHARACTER VARYING(50) NOT NULL,\n" +
            "\tuser_email CHARACTER VARYING(80),\n" +
            "\tid_role INTEGER REFERENCES table_role(id_role) NOT NULL\n" +
            ");\n" +
            "\n" +
            "\n" +
            "CREATE TABLE table_event\n" +
            "(\n" +
            "\tid_book INTEGER REFERENCES table_book(id_book) NOT NULL,\n" +
            "\tid_user INTEGER REFERENCES table_user(id_user) NOT NULL,\n" +
            "\tfdate DATE NOT NULL,\n" +
            "\tcounter INTEGER NOT NULL\n" +
            ");\n";
    static void DBDriver() throws ClassNotFoundException {
        //Драйвер postgresql загружен
        System.out.println("Проверка доступности драйвера: postgresql");
        Class.forName("org.postgresql.Driver");
        System.out.println("Драйвер доступен");
    }

    static int DBCreate() throws SQLException {
        System.out.println("Проверка доступности базы данных: LabraryDB");
        //Выполняем соединение с postgresql
        Connection con = DriverManager.getConnection(url,login,password);
        //Формируем параметр выражения для обработки запросов
        Statement st = con.createStatement();
        //Задаем запрос
        int result = st.executeUpdate("CREATE DATABASE labrarydb;");
        con.close();
        if(result==0) {//Проверка результата
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/labrarydb","postgres","12345");
            Statement statement = connection.createStatement();
            int result_tab = statement.executeUpdate(sql_tab);
            if(result_tab!=0)
            {
                System.err.println("Ошибка создания таблиц!");
            }else {
                System.out.println("Таблицы созданы!");
            }
        }else{
            System.err.println("Ошибка создания базы LabraryDB");
        }
        return result;
    }
}
