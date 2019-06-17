package com.company.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBService {

    static DBService instance;

    private DBService() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1/jdbc_cesi?user=root&password=coaxys&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris";
            connection = DriverManager.getConnection(url);
        } catch (ClassNotFoundException | SQLException e) {
            System.exit(1);
        }
    }

    public static DBService get() {
        if (instance == null) {
            instance = new DBService();
        }
        return instance;
    }

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

}
