package com.rafiatu.library_management_system.config;

import java.sql.Connection;

//class loader
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Database {
    private static Connection connection;

    Database() throws ClassNotFoundException {
//        Class.forName("org.postgresql.Driver");

    }

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            String dbUrl = "jdbc:postgresql://localhost:5432/postgres";
            String dbUser = "postgres";
            String dbPassword = "123456789";
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        }

        return connection;
    }
}
