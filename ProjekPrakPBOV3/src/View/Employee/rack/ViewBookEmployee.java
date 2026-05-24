package View.Employee.rack;

import Controller.ControllerBook;
import Controller.SessionManager;
import Model.Book.ModelTableBook;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.regex.Pattern;

public class ViewBookEmployee extends JFrame {

    private final JTable     tableBook = new JTable();
    private final JTextField tfSearch  = new JTextField();
    private final JButton    btnTambah = new JButton("+ Tambah Buku");
    private final JButton    btnEdit   = new JButton("Edit");
    private final JButton    btnHapus  = new JButton("Hapus");
    private final JButton    btnBack   = new JButton("Kembali");

    private final ControllerBook ctrl = new ControllerBook();
    private TableRowSorter<ModelTableBook> sorter;
    private final int idRack;
    private final String rackName;
    boolean isMgr = SessionManager.getInstance().isManager();

    public ViewBookEmployee(int idRack, String rackName) {
        this.idRack   = idRack;
        this.rackName = rackName;

        setTitle("Buku - Rak " + rackName);
        setSize(940, 560);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(248, 248, 252));

        JLabel lblTitle = new JLabel("Buku di Rak: " + rackName);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 14));
        lblTitle.setBounds(20, 12, 500, 28);

        JLabel lblFilter = new JLabel("Filter:");
        lblFilter.setBounds(20, 50, 40, 25);
        tfSearch.setBounds(62, 50, 260, 25);

        JScrollPane scroll = new JScrollPane(tableBook);
        scroll.setBounds(20, 85, 890, 380);
        tableBook.setRowHeight(24);

        btnTambah.setBounds(20,  483, 140, 32);
        btnEdit  .setBounds(175, 483, 100, 32);
        btnHapus .setBounds(288, 483, 100, 32);
        btnBack  .setBounds(800, 483, 110, 32);

        styleBtn(btnTambah);
        styleBtn(btnEdit);
        styleBtn(btnHapus);
        styleBtn(btnBack);

        if (!isMgr) {
            btnTambah.setEnabled(false);
            btnEdit.setEnabled(false);
            btnHapus.setEnabled(false);
        }

        add(lblTitle); add(lblFilter); add(tfSearch);
        add(scroll); add(btnTambah); add(btnEdit); add(btnHapus); add(btnBack);

        loadBook();

        tfSearch.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e)  { applyFilter(); }
            public void removeUpdate(DocumentEvent e)  { applyFilter(); }
            public void changedUpdate(DocumentEvent e) { applyFilter(); }
        });

        btnTambah.addActionListener(e -> new InputBookDialog(this, idRack));

        btnEdit.addActionListener(e -> {
            int row = getSelectedModelRow(); if (row < 0) return;
            int    id    = (int) tableBook.getModel().getValueAt(row, 0);
            String title = tableBook.getModel().getValueAt(row, 1).toString();
            String auth  = tableBook.getModel().getValueAt(row, 2).toString();
            int    year  = (int) tableBook.getModel().getValueAt(row, 3);
            int    stock = (int) tableBook.getModel().getValueAt(row, 4);
            new EditBookDialog(this, id, title, auth, year, stock, idRack);
        });

        btnHapus.addActionListener(e -> {
            int row = getSelectedModelRow(); if (row < 0) return;
            int    id    = (int) tableBook.getModel().getValueAt(row, 0);
            String title = tableBook.getModel().getValueAt(row, 1).toString();
            ctrl.delete(id, title, this::loadBook);
        });

        btnBack.addActionListener(e -> { dispose(); new ViewRackEmployee(); });

        setVisible(true);
    }

    public void loadBook() {
        ctrl.loadByRack(idRack, tableBook);
        SwingUtilities.invokeLater(() -> {
            if (tableBook.getRowSorter() instanceof TableRowSorter) {
                sorter = (TableRowSorter<ModelTableBook>) tableBook.getRowSorter();
            }
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

    public int    getIdRack()    { return idRack; }
    public String getRackName()  { return rackName; }
}
