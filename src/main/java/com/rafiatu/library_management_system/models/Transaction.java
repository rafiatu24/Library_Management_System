package com.rafiatu.library_management_system.models;

import com.rafiatu.library_management_system.config.Auth;
import com.rafiatu.library_management_system.config.Database;
import com.rafiatu.library_management_system.config.Util;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class Transaction {
    private int id;
    private int book_id;
    private int user_id = Auth.id;

    public int getId() {
        return id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public boolean isIs_returned() {
        return is_returned;
    }

    public void setIs_returned(boolean is_returned) {
        this.is_returned = is_returned;
    }

    private boolean is_returned;

    public void borrow() {
        String transactionSql = "INSERT INTO transactions (user_id, book_id) VALUES (?, ?)";
        String bookSql = "UPDATE books SET available = false WHERE id = ?";
        ArrayList<ArrayList<String>> transactionList = new ArrayList<ArrayList<String>>();

        try (PreparedStatement statement = Database.getConnection().prepareStatement(transactionSql)) {
            statement.setInt(1, user_id);
            statement.setInt(2, book_id);
            statement.execute();
        } catch (SQLException e) {
            System.err.println("Error while adding transaction: " + e.getMessage());
        }

        try (PreparedStatement statement = Database.getConnection().prepareStatement(bookSql)) {
            statement.setInt(1, book_id);
            statement.execute();
        } catch (SQLException e) {
            System.err.println("Error while updating book availability: " + e.getMessage());
        }

    }

    public static String[][] getUserTransactions() {
        String sql = "select * from transactions join books on transactions.book_id = books.id where user_id = ?";
        ArrayList<ArrayList<String>> userTransactions = new ArrayList<ArrayList<String>>();
        try (PreparedStatement statement = Database.getConnection().prepareStatement(sql)) {
            statement.setInt(1, Auth.id);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                userTransactions.add(new ArrayList<String>(Arrays.asList(
                        result.getString("title"), result.getString("author"), result.getString("genre"), result.getBoolean("is_returned")? "RETURNED" : "BORROWED"
                )));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Util.convert(userTransactions);
    }

}