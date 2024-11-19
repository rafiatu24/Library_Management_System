//package com.rafiatu.library_management_system.config;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//
//public class databaseConfig {
//    private Connection conn;
//
//    public databaseConfig(){
//        Class.forName("org.postgresql.Driver");
//        String dbUrl = "jdbc:postgresql://localhost:5432/library_db";
//        String dbUser = "postgres";
//        String dbPassword = "123456789";
//
//        this.conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
//    }
//
//    public Connection getConnection() {
//        return this.conn;
//    }
//
//    public void createTable() {
//        String createTableSQL = " CREATE TABLE IF NOT EXISTS books ( id SERIAL PRIMARY KEY, title VARCHAR(255) NOT NULL, author VARCHAR(255) NOT NULL, published_year INT, genre VARCHAR(100), is_available BOOLEAN DEFAULT TRUE ); ";
//        try (Statement statement = this.conn.createStatement()) {
//            statement.execute(createTableSQL);
//            System.out.println("Table 'books' created successfully in the database.");
//        } catch (Exception e) {
//            System.err.println("Error while creating the table: " + e.getMessage());
//        }
//    }
//
//    public void runMigrations() {
//    }
//}
//
//
//
//
//
//
//
//
//
//
