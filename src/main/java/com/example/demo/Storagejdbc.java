package com.example.demo;

import java.sql.*;

public class Storagejdbc {
    static final String url = "jdbc:mysql://localhost:3307/demo_db";
    static final String username = "rootsql";
    static final String password = "123456";

    // do something in database, then close connection

    public static final String DB_Driver = "org.h2.Driver";

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            System.out.println("Соединение с СУБД выполнено.");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM task");
            connection.close();       // отключение от БД
            System.out.println("Отключение от СУБД выполнено.");
            while (resultSet.next()) {
                System.out.println(
                        String.format("Task id: %d, Text: %s, Status:%s",
                                resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)
                        )
                );

            }
            statement.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace(); // обработка ошибок  DriverManager.getConnection
            System.out.println(e);
            System.out.println("Ошибка SQL !");
        }
    }

}
