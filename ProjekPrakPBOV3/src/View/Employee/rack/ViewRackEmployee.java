package View.Employee.rack;

import Controller.ControllerBook;
import Controller.ControllerRack;
import Controller.SessionManager;
import Model.Rack.ModelTableRack;
import View.Employee.EmployeeDashboard;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.regex.Pattern;

public class ViewRackEmployee extends JFrame {

    private final JTable      tableRack  = new JTable();
    private final JScrollPane scrollRack = new JScrollPane(tableRack);
    private final JTextField  tfSearch   = new JTextField();
    private final JButton     btnTambah  = new JButton("+ Tambah Rak");
    private final JButton     btnEdit    = new JButton("Edit");
    private final JButton     btnHapus   = new JButton("Hapus");
    private final JButton     btnBuka    = new JButton("Buka Rak");
    private final JButton     btnCariBook= new JButton("Cari Buku");
    private final JButton     btnBack    = new JButton("Kembali");

    private final ControllerRack  ctrlRack = new ControllerRack();
    private final ControllerBook  ctrlBook = new ControllerBook();
    private TableRowSorter<ModelTableRack> sorter;

    private final JTextField  tfSearchBook  = new JTextField();
    private final JTable      tableBook     = new JTable();
    private final JScrollPane scrollBook    = new JScrollPane(tableBook);
    private final JButton     btnSearchExec = new JButton("Cari");
    private final JButton     btnCloseSearch= new JButton("Tutup");

    boolean isManager = SessionManager.getInstance().isManager();

    public ViewRackEmployee() {
        setTitle("Manajemen Rak - " + SessionManager.getInstance().getEmployee().getUsername());
        setSize(940, 520);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(248, 248, 252));

        JLabel lblTitle = new JLabel("Daftar Rak Buku");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 15));
        lblTitle.setBounds(20, 12, 300, 28);

        JLabel lblFilter = new JLabel("Filter:");
        lblFilter.setBounds(20, 50, 40, 25);
        tfSearch.setBounds(62, 50, 260, 25);

        scrollRack.setBounds(20, 85, 890, 300);
        tableRack.setRowHeight(24);

        btnTambah .setBounds(20,  403, 130, 32);
        btnEdit   .setBounds(163, 403, 100, 32);
        btnHapus  .setBounds(276, 403, 100, 32);
        btnBuka   .setBounds(389, 403, 120, 32);
        btnCariBook.setBounds(522, 403, 120, 32);
        btnBack   .setBounds(790, 403, 110, 32);

        styleBtn(btnTambah);
        styleBtn(btnEdit);
        styleBtn(btnHapus);
        styleBtn(btnBuka);
        styleBtn(btnCariBook);
        styleBtn(btnBack);

        if (!isManager) {
            btnTambah.setEnabled(false);
            btnEdit.setEnabled(false);
            btnHapus.setEnabled(false);
        }

        JLabel lblSearchBook = new JLabel("Cari buku (semua rak):");
        lblSearchBook.setBounds(20, 452, 160, 24);
        tfSearchBook.setBounds(185, 452, 250, 26);
        btnSearchExec.setBounds(440, 452, 80, 26);
        btnCloseSearch.setBounds(530, 452, 80, 26);
        scrollBook.setBounds(20, 484, 890, 120);
        tableBook.setRowHeight(22);

        styleBtn(btnSearchExec);
        styleBtn(btnCloseSearch);
        setSearchVisible(false);

        add(lblTitle); add(lblFilter); add(tfSearch);
        add(scrollRack);
        add(btnTambah); add(btnEdit); add(btnHapus); add(btnBuka);
        add(btnCariBook); add(btnBack);
        add(lblSearchBook); add(tfSearchBook); add(btnSearchExec);
        add(btnCloseSearch); add(scrollBook);

        loadRack();

        tfSearch.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e)  { applyFilter(); }
            public void removeUpdate(DocumentEvent e)  { applyFilter(); }
            public void changedUpdate(DocumentEvent e) { applyFilter(); }
        });

        btnTambah.addActionListener(e -> new InputRackDialog(this));

        btnEdit.addActionListener(e -> {
            int row = getSelectedModelRow(); if (row < 0) return;
            int    id  = (int)    tableRack.getModel().getValueAt(row, 0);
            String nm  =          tableRack.getModel().getValueAt(row, 1).toString();
            String cat =          tableRack.getModel().getValueAt(row, 2).toString();
            String loc =          tableRack.getModel().getValueAt(row, 3).toString();
            new EditRackDialog(this, id, nm, cat, loc);
        });

        btnHapus.addActionListener(e -> {
            int row = getSelectedModelRow(); if (row < 0) return;
            int    id  = (int) tableRack.getModel().getValueAt(row, 0);
            String nm  =       tableRack.getModel().getValueAt(row, 1).toString();
            ctrlRack.delete(id, nm, this::loadRack);
        });

        btnBuka.addActionListener(e -> {
            int row = getSelectedModelRow(); if (row < 0) return;
            int    id  = (int) tableRack.getModel().getValueAt(row, 0);
            String nm  =       tableRack.getModel().getValueAt(row, 1).toString();
            dispose();
            new ViewBookEmployee(id, nm);
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

        btnBack.addActionListener(e -> { dispose(); new EmployeeDashboard(); });

        setVisible(true);
    }

    public void loadRack() {
        ctrlRack.loadAll(tableRack, () -> {
            sorter = (TableRowSorter<ModelTableRack>) tableRack.getRowSorter();
        });
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
        for (Component c : getContentPane().getComponents()) {
            if (c instanceof JLabel && ((JLabel)c).getText().startsWith("Cari buku"))
                c.setVisible(v);
        }
        setSize(940, v ? 650 : 520);
    }

    private void styleBtn(JButton b) {
        b.setBackground(Color.BLACK);
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Arial", Font.BOLD, 12));
        b.setFocusPainted(false);
        b.setBorderPainted(false);
    }
}
