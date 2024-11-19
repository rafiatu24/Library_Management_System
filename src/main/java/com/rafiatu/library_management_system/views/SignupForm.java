package com.rafiatu.library_management_system.views;

import com.rafiatu.library_management_system.models.User;

import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;

public class SignupForm {
    private JPanel frame;
    private JTextField username;
    private JButton signupButton;
    private JTextField email;
    private JPasswordField password;
    private JPasswordField password_confirmation;
    private JLabel usernameError;
    private JLabel emailError;
    private JLabel passwordError;
    private JLabel signIn;
    JFrame signUpPage = new JFrame("Sign Up Form");

    public SignupForm() {
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (validate()) {

                        if (save()) {
                            signUpPage.dispose();
                            (new Dashboard()).show();
                        }
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        signIn.addComponentListener(new ComponentAdapter() {
        });
        signIn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                signUpPage.dispose();
                (new LoginForm()).show();
            }
        });
    }

    public void show() {
        signUpPage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        signUpPage.setContentPane(frame);
        signUpPage.pack();
        signUpPage.setVisible(true);
    }

    public boolean validate() throws SQLException {
        // todo: validate email format
        sanitizeInput();
        clearValidationMessages();
        checkEmptyValues();
        checkPasswordMatch();
        checkForEmailDuplicates();
        signUpPage.pack();
        return isValid();
    }

    private void checkEmptyValues() {
        if (this.username.getText().equals("")) {
            usernameError.setText("Username is required");
        }
        if (this.email.getText().equals("")) {
            emailError.setText("Email is required");
        }
        if (this.password.getText().equals("")) {
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
        if (!this.usernameError.getText().equals("")) {
            return false;
        }
        if (!this.emailError.getText().equals("")) {
            return false;
        }
        if (!this.passwordError.getText().equals("")) {
            return false;
        }

        return true;
    }

    private boolean save() throws SQLException {
        System.out.println("saving user data");
        User user = new User();
        user.setUsername(username.getText());
        user.setEmail(email.getText());
        user.setPassword(new String(password.getPassword()));
        return user.save();
    }

    private void checkForEmailDuplicates() throws SQLException {
        if (User.checkForEmail(email.getText())) {
            emailError.setText("Email already exists");
        }
    }
}
