package com.rafiatu.library_management_system.views;

import com.rafiatu.library_management_system.config.Auth;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Component {
    private static final JFrame dashboardLayout = new JFrame("Dashboard");
    public static JFrame getDashboardLayout() {
        dashboardLayout.setJMenuBar(getMenuBar());
        dashboardLayout.pack();
        dashboardLayout.setVisible(true);

        return dashboardLayout;
    }

    public static JMenuBar getMenuBar(){
        JMenuBar menuBar = new JMenuBar();

        JMenu bookMenu = new JMenu("Books");
        JMenuItem addBook = new JMenuItem("add");
        JMenuItem listBook = new JMenuItem("list");
        if (Auth.is_patron) {
            bookMenu.add(addBook);
        }
        bookMenu.add(listBook);

        addBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                (new BookView()).showForm();
            }
        });

        listBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    (new BookView()).showList();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JMenu transactionMenu = new JMenu("Transactions");
        JMenuItem listTransaction = new JMenuItem("list");
        listTransaction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                (new TransactionView()).show();
            }
        });
        transactionMenu.add(listTransaction);

        JMenu patronMenu = new JMenu("Patron");
        JMenuItem addPatron = new JMenuItem("add");
        addPatron.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                (new PatronView()).showForm();
            }
        });
        JMenuItem listPatron = new JMenuItem("list");
        listPatron.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                (new PatronView()).showList();
            }
        });
        patronMenu.add(addPatron);
        patronMenu.add(listPatron);

        JMenuItem logout = new JMenuItem("logout");
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Component.getDashboardLayout().dispose();
                (new LoginForm()).show();
            }
        });

        menuBar.add(bookMenu);
        menuBar.add(transactionMenu);
        if (Auth.is_patron) {
            menuBar.add(patronMenu);
        }
        menuBar.add(logout);

        return menuBar;
    }
}
