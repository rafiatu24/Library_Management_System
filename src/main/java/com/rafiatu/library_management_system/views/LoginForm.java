package com.rafiatu.library_management_system.views;

import com.rafiatu.library_management_system.models.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.Arrays;

public class LoginForm {
    private JPanel panel;
    private JTextField email;
    private JButton loginButton;
    private JPasswordField password;
    private JLabel emailError;
    private JLabel passwordError;
    private JLabel signUpButton;
    private JPasswordField passwordField1;
    private JFrame frame = new JFrame("Login Form");

    public LoginForm() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validate()){
                    try {
                        signIn();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                frame.dispose();
                (new SignupForm()).show();
            }
        });
    }

    public void show(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private boolean validate() {
        sanitizeInput();
        clearValidationMessages();
        checkEmptyValues();
        return isValid();
    }

    private void signIn() throws SQLException {
        if (User.login(email.getText(), new String(passwordField1.getPassword()))){
            frame.dispose();
            (new Dashboard()).show();
        }else {
            clear();
            passwordError.setText("Invalid email or password");
        };
    }

    private void sanitizeInput() {
        email.setText(email.getText().strip());
        password.setText(Arrays.toString(password.getPassword()));
    }

    private void clearValidationMessages() {
        emailError.setText("");
        passwordError.setText("");
    }

    private void checkEmptyValues() {
        if (this.email.getText().equals("")) {
            emailError.setText("Email is required");
        }
        if (this.password.getText().equals("")) {
            passwordError.setText("Password is required");
        }
    }

    private boolean isValid() {
        if (!this.emailError.getText().equals("")) {
            return false;
        }
        if (!this.passwordError.getText().equals("")) {
            return false;
        }

        return true;
    }

    private void clear() {
        this.email.setText("");
        this.password.setText("");
    }

}
