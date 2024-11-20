package com.rafiatu.library_management_system.views;

import com.rafiatu.library_management_system.models.Book;
import com.rafiatu.library_management_system.models.Transaction;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class BookView {
    private JTextField title;
    private JTextField author;
    private JLabel usernameError;
    private JTextField genre;
    private JPanel contentPane;
    private JButton addButton;
    private JLabel titleError;
    private JLabel authorError;
    private JLabel genreError;

    public BookView() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (validate()) {
                    try {
                        if (save()) {
                            showList();
                        };
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
    }

    public void showForm() {
        JFrame dashboardLayout = Component.getDashboardLayout();
        dashboardLayout.setContentPane(contentPane);
        dashboardLayout.setSize(300, 350);
    }

    public void showList() throws SQLException {
        String[] columns = {"Title", "Author", "Genre", "Action"};
        String[][] availableBooks = Book.getAvailableBooks();

        JTable table = new JTable(availableBooks, columns);
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        table.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
        table.getColumnModel().getColumn(3).setCellRenderer(new ButtonRenderer());
        table.getColumnModel().getColumn(3).setCellEditor(new ButtonEditor(new JCheckBox()));
        JScrollPane scrollPane = new JScrollPane(table);

        JFrame dashboardLayout = Component.getDashboardLayout();
        dashboardLayout.setContentPane(scrollPane);
        dashboardLayout.pack();
    }

    public boolean validate() {
        sanitizeInput();
        clearValidationMessages();
        checkEmptyValues();
        return isValid();
    }

    private void sanitizeInput() {
        title.setText(title.getText().strip());
        author.setText(author.getText().strip());
        genre.setText(genre.getText().strip());
    }

    private void clearValidationMessages() {
        titleError.setText("");
        authorError.setText("");
        genreError.setText("");
    }

    private void checkEmptyValues() {
        if (title.getText().isEmpty()) {
            titleError.setText("Title is required");
        }
        if (author.getText().isEmpty()) {
            authorError.setText("Author is required");
        }
        if (genre.getText().isEmpty()) {
            genreError.setText("Genre is required");
        }
    }

    private boolean isValid() {
        if (!titleError.getText().isEmpty()) {
            return false;
        }
        if (!authorError.getText().isEmpty()) {
            return false;
        }
        if (!genreError.getText().isEmpty()) {
            return false;
        }

        return true;
    }

    private boolean save() throws SQLException {
        System.out.println("saving book data");
        Book book = new Book();
        book.setTitle(title.getText());
        book.setAuthor(author.getText());
        book.setGenre(genre.getText());
        return book.save();
    }


    /**
     * Button Renderer Test Class
     */
    class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setOpaque(true);
        }

        public java.awt.Component getTableCellRendererComponent(JTable table, Object value,
                                                                boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(UIManager.getColor("Button.background"));
            }
            setText("Borrow");

            return this;
        }
    }
}

class ButtonEditor extends DefaultCellEditor {
    protected JButton button;
    private String label;
    private boolean isPushed;
    private int key;

    public ButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
                Transaction transaction = new Transaction();
                transaction.setBook_id(key);
                transaction.borrow();
                (new TransactionView()).show();
            }
        });
    }

    public java.awt.Component getTableCellEditorComponent(JTable table, Object value,
                                                          boolean isSelected, int row, int column) {
        key = Integer.parseInt((String) value);
        button.setText(label);
        isPushed = true;
        return button;
    }

    public Object getCellEditorValue() {
        isPushed = false;
        return label;
    }
}