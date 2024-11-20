package com.rafiatu.library_management_system.views;

import com.rafiatu.library_management_system.models.Book;
import com.rafiatu.library_management_system.models.User;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PatronView {
    private JTextField username;
    private JTextField email;
    private JPasswordField password;
    private JPasswordField password_confirmation;
    private JButton addPatronButton;
    private JLabel usernameError;
    private JLabel emailError;
    private JLabel passwordError;
    private JPanel contentFrame;

    public PatronView() {
        addPatronButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (validate()) {
                        save();
                        showList();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    public void showForm() {
        JFrame dashboard = Component.getDashboardLayout();
        dashboard.setContentPane(contentFrame);
        dashboard.setSize(250,400);
    }

    public void showList() {
        String[] columns = {"Username", "email"};
        String[][] patrons = User.getPatrons();

        JTable table = new JTable(patrons, columns);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
        JScrollPane scrollPane = new JScrollPane(table);

        JFrame dashboardLayout = Component.getDashboardLayout();
        dashboardLayout.setContentPane(scrollPane);
        dashboardLayout.pack();
    }

    public boolean validate() throws SQLException {
        // todo: validate email format
        sanitizeInput();
        clearValidationMessages();
        checkEmptyValues();
        checkPasswordMatch();
        checkForEmailDuplicates();
        return isValid();
    }

    private void checkEmptyValues() {
        if (this.username.getText().isEmpty()) {
            usernameError.setText("Username is required");
        }
        if (this.email.getText().isEmpty()) {
            emailError.setText("Email is required");
        }
        if (this.password.getText().isEmpty()) {
            passwordError.setText("Password is required");
        }
    }

    private void checkPasswordMatch() {
        if (!this.password.getText().equals(this.password_confirmation.getText())) {
            passwordError.setText("Passwords do not match");
        }
    }

    private void clearValidationMessages() {
        usernameError.setText("");
        emailError.setText("");
        passwordError.setText("");
    }

    private void sanitizeInput() {
        username.setText(username.getText().strip());
        email.setText(email.getText().strip());
        password.setText(password.getText().strip());
        password_confirmation.setText(password_confirmation.getText().strip());
    }

    private boolean isValid() {
//        return !usernameError.getText().isEmpty() && !emailError.getText().isEmpty() && !passwordError.getText().isEmpty();
        if (!this.usernameError.getText().isEmpty()) {
            return false;
        }
        if (!this.emailError.getText().isEmpty()) {
            return false;
        }
        if (!this.passwordError.getText().isEmpty()) {
            return false;
        }

        return true;
    }

    private void checkForEmailDuplicates() throws SQLException {
        if (User.checkForEmail(email.getText())) {
            emailError.setText("Email already exists");
        }
    }

    private boolean save() throws SQLException {
        System.out.println("saving Patron data");
        User user = new User();
        user.setUsername(username.getText());
        user.setEmail(email.getText());
        user.setPassword(new String(password.getPassword()));
        user.setIs_patron(true);
        return user.save();
    }
}
