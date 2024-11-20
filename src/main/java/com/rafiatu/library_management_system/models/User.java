package com.rafiatu.library_management_system.models;

import com.rafiatu.library_management_system.config.Auth;
import com.rafiatu.library_management_system.config.Database;
import com.rafiatu.library_management_system.config.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;

public class User extends Model{
    private static String table = "users";
    private int id;
    private String username;
    private String password;
    private String email;
    private boolean is_patron = false;

    public User() throws SQLException {
    }

    public static String[][] getPatrons() {
        ArrayList<ArrayList<String>> patrons = new ArrayList<ArrayList<String>>();
        String sql = "SELECT * FROM users WHERE is_patron = true";
        try (PreparedStatement statement = Database.getConnection().prepareStatement(sql)) {
            ResultSet result = statement.executeQuery();
            while (result.next()) {
                patrons.add(new ArrayList<String>(Arrays.asList(
                        result.getString("username"), result.getString("email")
                )));
            }
        } catch (SQLException e) {
            System.err.println("Error getting patrons: " + e.getMessage());
        }
        return Util.convert(patrons);
    }

    public boolean is_patron() {
        return is_patron;
    }

    public void setIs_patron(boolean is_patron) {
        this.is_patron = is_patron;
    }

    public int getId() {
        return id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public boolean save(){
        String sql = "INSERT INTO " + table + " (username, password, email, is_patron) VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = Database.getConnection().prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, email);
            statement.setBoolean(4, is_patron);
            statement.execute();
            Auth.email = email;
            Auth.username = username;
            Auth.is_patron = is_patron;
            System.out.println("User created successfully");
            return true;
        } catch (Exception e) {
            System.err.println("Error while creating user: " + e.getMessage());
        }
        return false;
    }

    public static boolean login(String email, String password) throws SQLException {
        String sql = "SELECT * FROM " + table + " WHERE email = ? AND password = ?";
        try (PreparedStatement statement = Database.getConnection().prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Auth.id = result.getInt("id");
                Auth.username = result.getString("username");
                Auth.email = result.getString("email");
                Auth.is_patron = result.getBoolean("is_patron");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error while logging in: " + e.getMessage());
        }
        return false;
    }

    public static boolean checkForEmail(String email) throws SQLException {
        String sql = "SELECT * FROM " + table + " WHERE email = ?";
        try (PreparedStatement statement = Database.getConnection().prepareStatement(sql)) {
            statement.setString(1, email);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error while checking for email: " + e.getMessage());
        }
        return false;
    }
}
