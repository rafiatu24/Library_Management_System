package com.rafiatu.library_management_system.views;

import javax.swing.*;
import java.awt.*;

public class Dashboard {
    JFrame dashboard = new JFrame("Dashboard");

    public void show(){
        JPanel panel = new JPanel();
        BorderLayout borderLayout = new BorderLayout();
        panel.setLayout(borderLayout);
        panel.add((new JLabel("Welcome {name}")), BorderLayout.CENTER);

        dashboard.setJMenuBar(getMenuBar());
        dashboard.setContentPane(panel);
        dashboard.pack();
        dashboard.setVisible(true);
    }

    private JMenuBar getMenuBar(){
        JMenuBar menuBar = new JMenuBar();

        JMenu bookMenu = new JMenu("Books");
        JMenuItem addBook = new JMenuItem("add");
        JMenuItem listBook = new JMenuItem("list");
        bookMenu.add(addBook);
        bookMenu.add(listBook);

        JMenu transactionMenu = new JMenu("Transactions");
        JMenuItem addTransaction = new JMenuItem("add");
        JMenuItem listTransaction = new JMenuItem("list");
        transactionMenu.add(addTransaction);
        transactionMenu.add(listTransaction);

        JMenu patronMenu = new JMenu("Patron");
        JMenuItem addPatron = new JMenuItem("add");
        JMenuItem listPatron = new JMenuItem("list");
        patronMenu.add(addPatron);
        patronMenu.add(listPatron);

        menuBar.add(bookMenu);
        menuBar.add(transactionMenu);
        menuBar.add(patronMenu);

        return menuBar;
    }
}
