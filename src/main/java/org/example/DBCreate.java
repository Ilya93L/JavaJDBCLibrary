package org.example;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBCreate {
    final static String url = "jdbc:postgresql://localhost/?";
    final static String login = "postgres";
    final static String password ="12345";
    private final static String sql_crt_tab_author= """
                                                    create table table_author
                                                    (
                                                        id_author SERIAL PRIMARY KEY,
                                                        author_name CHARACTER VARYING(50) not null,
                                                        author_surname CHARACTER VARYING(50) not null,
                                                        author_patronymic CHARACTER VARYING(50) not null
                                                    );
                                                    """;
    private final static String sql_crt_tab_genre= """
                                                    create table table_genre
                                                    (
                                                        id_genre SERIAL PRIMARY KEY,
                                                        title_genre CHARACTER VARYING(50) not null
                                                    );                   
                                                    """;
    private final static String sql_crt_tab_book= """
                                                    create table table_book
                                                    (
                                                        id_book SERIAL PRIMARY KEY,
                                                        title_book CHARACTER VARYING(100) NOT null,
                                                        id_genre INTEGER REFERENCES table_genre(id_genre) NOT null,
                                                        id_author INTEGER REFERENCES table_author(id_author) NOT null
                                                    );                 
                                                    """;
    private final static String sql_crt_tab_role= """
                                                    CREATE TABLE table_role
                                                    (
                                                        id_role SERIAL PRIMARY KEY,
                                                        role CHARACTER VARYING(30)
                                                    );                
                                                    """;
    private final static String sql_crt_tab_user= """
                                                    CREATE TABLE table_user
                                                    (
                                                        id_user SERIAL PRIMARY KEY,
                                                        user_name CHARACTER VARYING(50)NOT NULL,
                                                        user_surname CHARACTER VARYING(50)NOT NULL,
                                                        user_patronymic CHARACTER VARYING(50)NOT NULL,
                                                        user_telephone CHARACTER VARYING(50)NOT NULL,
                                                        user_address CHARACTER VARYING(50)NOT NULL,
                                                        user_birthday DATE NOT NULL,
                                                        user_login CHARACTER VARYING(50) NOT NULL,
                                                        user_password CHARACTER VARYING(50) NOT NULL,
                                                        user_email CHARACTER VARYING(80),
                                                        id_role INTEGER REFERENCES table_role(id_role) NOT NULL
                                                    );                        
                                                    """;
    private final static String sql_crt_tab_event= """
                                                    CREATE TABLE table_event
                                                    (
                                                        id_book INTEGER REFERENCES table_book(id_book) NOT NULL,
                                                        id_user INTEGER REFERENCES table_user(id_user) NOT NULL,
                                                        fdate DATE NOT NULL,
                                                        counter INTEGER NOT NULL
                                                    );                      
                                                    """;
    private final static String sql_ins_user1= """
                                                INSERT INTO table_user(
                                                user_name,user_surname,user_patronymic,user_telephone,user_address,user_birthday,user_login,user_password,user_email,id_role)
                                                VALUES('admin','admin','administrator','+79001234567','Россия','11.12.1995','admin','admin','adminlibrary93@mail.ru',1);                        
                                                """;
    private final static String sql_ins_user2= """
                                                INSERT INTO table_user(
                                                user_name,user_surname,user_patronymic,user_telephone,user_address,user_birthday,user_login,user_password,user_email,id_role)
                                                VALUES('Тест','Тесто','Тестовый','+79007654321','Россия','01.02.1990','user','user','testlibrary93@mail.ru',2); 
                                                """;
    private final static String sql_ins_role1= """
                                                INSERT INTO table_role(role) VALUES ('Администратор');
                                                """;
    private final static String sql_ins_role2= """
                                                INSERT INTO table_role(role) VALUES ('Пользователь');
                                                """;
    static void DBDriver() throws ClassNotFoundException {
        //Драйвер postgresql загружен
        System.out.println("Проверка доступности драйвера: postgresql");
        Class.forName("org.postgresql.Driver");
        System.out.println("Драйвер доступен");
    }
    private static int QuerySql(Connection connect,String sql,String info,String name) throws SQLException {
        System.out.print(info+name);
        Statement statement = connect.createStatement();
        int result = statement.executeUpdate(sql);
        System.out.println(" ...OK");
        return result;
    }
    static int DBCreate() throws SQLException {
        System.out.println("Проверка доступности базы данных: LabraryDB");
        //Выполняем соединение с postgresql
        Connection con = DriverManager.getConnection(url,login,password);
        //Формируем параметр выражения для обработки запросов
        Statement st = con.createStatement();
        //Задаем запрос
        st.executeUpdate("CREATE DATABASE labrarydb;");
        con.close();
        try(Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/labrarydb","postgres","12345")) {
            QuerySql(connection, sql_crt_tab_author, "Создание таблицы ", "table_author");
            QuerySql(connection, sql_crt_tab_genre, "Создание таблицы ", "table_genre");
            QuerySql(connection, sql_crt_tab_book, "Создание таблицы ", "table_book");
            QuerySql(connection, sql_crt_tab_role, "Создание таблицы ", "table_role");
            QuerySql(connection, sql_crt_tab_user, "Создание таблицы ", "table_user");
            QuerySql(connection, sql_crt_tab_event, "Создание таблицы ", "table_event");

            QuerySql(connection, sql_ins_role1, "Добавление роли:", "Администратор");
            QuerySql(connection, sql_ins_role2, "Добавление роли:", "Пользователь");
            QuerySql(connection, sql_ins_user1, "Добавление пользователя:", "Администратор");
            QuerySql(connection, sql_ins_user2, "Добавление пользователя:", "Тест");
        }
        return 0;
    }
}
