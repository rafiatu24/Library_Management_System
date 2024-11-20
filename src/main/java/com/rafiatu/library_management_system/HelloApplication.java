package com.rafiatu.library_management_system;


import com.rafiatu.library_management_system.views.BookView;
import com.rafiatu.library_management_system.views.SignupForm;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication {


    public static void main(String[] args) throws SQLException {
        SignupForm signupForm = new SignupForm();
        signupForm.show();
    }


}