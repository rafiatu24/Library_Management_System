package com.rafiatu.library_management_system.views;

import com.rafiatu.library_management_system.models.Book;
import com.rafiatu.library_management_system.models.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

public class TransactionView {
    public void show() {
        String[] columns = {"Title", "Author", "Genre", "type"};
        String[][] userTransactions = Transaction.getUserTransactions();

        JTable table = new JTable(userTransactions, columns);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(cellRenderer);
        JScrollPane scrollPane = new JScrollPane(table);

        JFrame dashboardLayout = Component.getDashboardLayout();
        dashboardLayout.setContentPane(scrollPane);
        dashboardLayout.pack();
    }
}
