package View.Member;

import Controller.ControllerBook;
import Controller.ControllerLoan;
import Controller.SessionManager;
import Model.Book.ModelTableBook;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.regex.Pattern;

public class ViewBookMember extends JFrame {

    private final JTable      tableBook = new JTable();
    private final JTextField  tfSearch  = new JTextField();
    private final JButton     btnPinjam = new JButton("Pinjam Buku");
    private final JButton     btnBack   = new JButton("Kembali");

    private final ControllerBook ctrlBook = new ControllerBook();
    private final ControllerLoan ctrlLoan = new ControllerLoan();
    private TableRowSorter<ModelTableBook> sorter;

    private final int    idRack;
    private final String rackName;

    public ViewBookMember(int idRack, String rackName) {
        this.idRack   = idRack;
        this.rackName = rackName;

        setTitle("Buku - Rak " + rackName);
        setSize(920, 540);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 250, 245));

        JLabel lblTitle = new JLabel("Buku di Rak: " + rackName);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 14));
        lblTitle.setBounds(20, 12, 500, 28);

        JLabel lblHint = new JLabel("Klik buku lalu tekan \"Pinjam Buku\" untuk meminjam (maks. 3 buku)");
        lblHint.setFont(new Font("Arial", Font.ITALIC, 11));
        lblHint.setForeground(new Color(80, 80, 80));
        lblHint.setBounds(20, 40, 750, 18);

        JLabel lblFilter = new JLabel("Filter:");
        lblFilter.setBounds(20, 68, 40, 25);
        tfSearch.setBounds(62, 68, 260, 25);

        JScrollPane scroll = new JScrollPane(tableBook);
        scroll.setBounds(20, 103, 870, 370);
        tableBook.setRowHeight(24);

        btnPinjam.setBounds(20,  488, 140, 32);
        btnBack  .setBounds(770, 488, 110, 32);

        styleBtn(btnPinjam);
        styleBtn(btnBack);

        add(lblTitle); add(lblHint); add(lblFilter); add(tfSearch);
        add(scroll); add(btnPinjam); add(btnBack);

        loadBook();

        tfSearch.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e)  { applyFilter(); }
            public void removeUpdate(DocumentEvent e)  { applyFilter(); }
            public void changedUpdate(DocumentEvent e) { applyFilter(); }
        });

        btnPinjam.addActionListener(e -> {
            int row = getSelectedModelRow(); if (row < 0) return;
            int    idBook = (int) tableBook.getModel().getValueAt(row, 0);
            String judul  =       tableBook.getModel().getValueAt(row, 1).toString();
            int    stok   = (int) tableBook.getModel().getValueAt(row, 4);

            if (stok <= 0) {
                JOptionPane.showMessageDialog(this,
                    "Stok buku habis! Tidak bisa dipinjam.",
                    "Stok Habis", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int idMember = SessionManager.getInstance().getMember().getIdMember();
            ctrlLoan.pinjam(idMember, idBook, judul, this::loadBook);
        });

        btnBack.addActionListener(e -> { dispose(); new ViewRackMember(); });

        setVisible(true);
    }

    private void loadBook() {
        ctrlBook.loadByRack(idRack, tableBook);
        SwingUtilities.invokeLater(() -> {
            if (tableBook.getRowSorter() instanceof TableRowSorter)
                sorter = (TableRowSorter<ModelTableBook>) tableBook.getRowSorter();
        });
    }

    private void applyFilter() {
        if (sorter == null) return;
        String t = tfSearch.getText().trim();
        sorter.setRowFilter(t.isEmpty() ? null :
            RowFilter.regexFilter("(?i)" + Pattern.quote(t)));
    }

    private int getSelectedModelRow() {
        int row = tableBook.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih buku terlebih dahulu!",
                "Peringatan", JOptionPane.WARNING_MESSAGE);
            return -1;
        }
        return tableBook.convertRowIndexToModel(row);
    }

    private void styleBtn(JButton b) {
        b.setBackground(Color.BLACK);
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Arial", Font.BOLD, 12));
        b.setFocusPainted(false);
        b.setBorderPainted(false);
    }
}
