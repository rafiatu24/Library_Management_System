package com.rafiatu.library_management_system.models;

//import jdk.internal.icu.text.UTF16;

import com.rafiatu.library_management_system.config.Database;
import com.rafiatu.library_management_system.config.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class Book extends Model{

//    public static UTF16 Genre;
    private int id;
    private String title;
    private String author;
    private String genre;
    private boolean available;

    public Book() throws SQLException {
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean save(){
        String sql = "INSERT INTO books (title, author, genre) VALUES (?, ?, ?)";

        try (PreparedStatement statement = Database.getConnection().prepareStatement(sql)) {
            statement.setString(1, title);
            statement.setString(2, author);
            statement.setString(3, genre);
            statement.execute();
            System.out.println("Book added successfully");
            return true;
        } catch (Exception e) {
            System.err.println("Error while adding book: " + e.getMessage());
        }
        return false;
    }

    public static String[][] getAvailableBooks() throws SQLException {
        ArrayList<ArrayList<String>> availableBooks = new ArrayList<ArrayList<String>>();
        String sql = "SELECT * FROM books WHERE available=true";
        try (PreparedStatement statement = Database.getConnection().prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while(result.next()){
                availableBooks.add(new ArrayList<String>(Arrays.asList(
                        result.getString("title"), result.getString("author"), result.getString("genre"), result.getString("id") // extra space for action button
                )));
            }
        }
        return Util.convert(availableBooks);
    }


}


