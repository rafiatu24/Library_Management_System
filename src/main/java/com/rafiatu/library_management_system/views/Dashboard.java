package com.rafiatu.library_management_system.views;

import com.rafiatu.library_management_system.config.Auth;

import javax.swing.*;
import java.awt.*;

public class Dashboard {

    public void show(){
        JPanel panel = new JPanel();
        BorderLayout borderLayout = new BorderLayout();
        panel.setLayout(borderLayout);
        panel.add((new JLabel("Welcome " + Auth.username)), BorderLayout.CENTER);

        JFrame dashboardLayout = Component.getDashboardLayout();
        dashboardLayout.setContentPane(panel);
        dashboardLayout.setSize(300, 300);

    }


}
