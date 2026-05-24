package View.Member;

import Controller.ControllerBook;
import Controller.ControllerRack;
import Model.Rack.ModelTableRack;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.regex.Pattern;

public class ViewRackMember extends JFrame {

    private final JTable      tableRack   = new JTable();
    private final JTextField  tfSearch    = new JTextField();
    private final JButton     btnBuka     = new JButton("Buka Rak");
    private final JButton     btnCariBook = new JButton("Cari Buku");
    private final JButton     btnBack     = new JButton("Kembali");

    private final ControllerRack ctrlRack = new ControllerRack();
    private final ControllerBook ctrlBook = new ControllerBook();
    private TableRowSorter<ModelTableRack> sorter;

    private final JTextField  tfSearchBook   = new JTextField();
    private final JTable      tableBook      = new JTable();
    private final JScrollPane scrollBook     = new JScrollPane(tableBook);
    private final JButton     btnSearchExec  = new JButton("Cari");
    private final JButton     btnCloseSearch = new JButton("Tutup");

    public ViewRackMember() {
        setTitle("Jelajahi Rak Buku");
        setSize(920, 520);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 250, 245));

        JLabel lblTitle = new JLabel("Pilih Rak Buku");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 15));
        lblTitle.setBounds(20, 12, 300, 28);

        JLabel lblFilter = new JLabel("Filter Rak:"); lblFilter.setBounds(20, 50, 70, 25);
        tfSearch.setBounds(95, 50, 240, 25);

        JScrollPane scrollRack = new JScrollPane(tableRack);
        scrollRack.setBounds(20, 85, 870, 290);
        tableRack.setRowHeight(24);

        btnBuka    .setBounds(20,  393, 120, 32);
        btnCariBook.setBounds(153, 393, 130, 32);
        btnBack    .setBounds(780, 393, 110, 32);

        styleBtn(btnBuka);
        styleBtn(btnCariBook);
        styleBtn(btnBack);

        JLabel lblSearchBook = new JLabel("Cari buku (semua rak):");
        lblSearchBook.setBounds(20, 440, 160, 24);
        tfSearchBook .setBounds(185, 440, 240, 26);
        btnSearchExec.setBounds(432, 440, 80, 26);
        btnCloseSearch.setBounds(522, 440, 80, 26);
        scrollBook   .setBounds(20,  472, 870, 130);
        tableBook.setRowHeight(22);

        styleBtn(btnSearchExec);
        styleBtn(btnCloseSearch);
        setSearchVisible(false);

        add(lblTitle); add(lblFilter); add(tfSearch);
        add(scrollRack);
        add(btnBuka); add(btnCariBook); add(btnBack);
        add(lblSearchBook); add(tfSearchBook);
        add(btnSearchExec); add(btnCloseSearch); add(scrollBook);

        loadRack();

        tfSearch.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e)  { applyFilter(); }
            public void removeUpdate(DocumentEvent e)  { applyFilter(); }
            public void changedUpdate(DocumentEvent e) { applyFilter(); }
        });

        btnBuka.addActionListener(e -> {
            int row = getSelectedModelRow(); if (row < 0) return;
            int    id   = (int) tableRack.getModel().getValueAt(row, 0);
            String name =       tableRack.getModel().getValueAt(row, 1).toString();
            dispose();
            new ViewBookMember(id, name);
        });

        btnCariBook.addActionListener(e -> {
            setSearchVisible(true);
            tfSearchBook.requestFocus();
        });

        btnSearchExec.addActionListener(e -> {
            String kw = tfSearchBook.getText().trim();
            if (!kw.isEmpty()) ctrlBook.searchGlobal(kw, tableBook);
        });

        btnCloseSearch.addActionListener(e -> {
            setSearchVisible(false);
            tfSearchBook.setText("");
        });

        btnBack.addActionListener(e -> { dispose(); new MemberDashboard(); });

        setVisible(true);
    }

    private void loadRack() {
        ctrlRack.loadAll(tableRack, () ->
            sorter = (TableRowSorter<ModelTableRack>) tableRack.getRowSorter());
    }

    private void applyFilter() {
        if (sorter == null) return;
        String t = tfSearch.getText().trim();
        sorter.setRowFilter(t.isEmpty() ? null :
            RowFilter.regexFilter("(?i)" + Pattern.quote(t)));
    }

    private int getSelectedModelRow() {
        int row = tableRack.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih rak terlebih dahulu!",
                "Peringatan", JOptionPane.WARNING_MESSAGE);
            return -1;
        }
        return tableRack.convertRowIndexToModel(row);
    }

    private void setSearchVisible(boolean v) {
        scrollBook.setVisible(v);
        tfSearchBook.setVisible(v);
        btnSearchExec.setVisible(v);
        btnCloseSearch.setVisible(v);
        for (java.awt.Component c : getContentPane().getComponents()) {
            if (c instanceof JLabel && ((JLabel) c).getText().startsWith("Cari buku"))
                c.setVisible(v);
        }
        setSize(920, v ? 640 : 520);
    }

    private void styleBtn(JButton b) {
        b.setBackground(Color.BLACK);
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Arial", Font.BOLD, 12));
        b.setFocusPainted(false);
        b.setBorderPainted(false);
    }
}
