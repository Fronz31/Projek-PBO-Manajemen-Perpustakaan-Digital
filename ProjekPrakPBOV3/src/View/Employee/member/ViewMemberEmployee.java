package View.Employee.member;

import Controller.ControllerMember;
import Model.Member.ModelTableMember;
import View.Employee.EmployeeDashboard;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.regex.Pattern;

public class ViewMemberEmployee extends JFrame {

    private final JTable     tableMember = new JTable();
    private final JTable     tableLoan   = new JTable();
    private final JTextField tfSearch    = new JTextField();
    private final JButton btnTambah      = new JButton("+ Tambah Member");
    private final JButton btnEdit        = new JButton("Edit");
    private final JButton btnHapus       = new JButton("Hapus");
    private final JButton btnDetail      = new JButton("Lihat Pinjaman");
    private final JButton btnBack        = new JButton("Kembali");

    private final ControllerMember ctrl = new ControllerMember();
    private TableRowSorter<ModelTableMember> sorter;

    public ViewMemberEmployee() {
        setTitle("Manajemen Member");
        setSize(980, 680);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(248, 248, 252));

        JLabel lblTitle = new JLabel("Daftar Member");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 15));
        lblTitle.setBounds(20, 12, 300, 28);

        JLabel lblFilter = new JLabel("Filter:"); lblFilter.setBounds(20, 50, 40, 25);
        tfSearch.setBounds(62, 50, 260, 25);

        JScrollPane scrollMember = new JScrollPane(tableMember);
        scrollMember.setBounds(20, 85, 930, 250);
        tableMember.setRowHeight(24);

        btnTambah.setBounds(20,  353, 145, 32);
        btnEdit  .setBounds(178, 353, 100, 32);
        btnHapus .setBounds(291, 353, 100, 32);
        btnDetail.setBounds(404, 353, 145, 32);
        btnBack  .setBounds(820, 353, 110, 32);

        styleBtn(btnTambah);
        styleBtn(btnEdit);
        styleBtn(btnHapus);
        styleBtn(btnDetail);
        styleBtn(btnBack);

        JLabel lblLoan = new JLabel("Riwayat Pinjaman Member:");
        lblLoan.setFont(new Font("Arial", Font.BOLD, 12));
        lblLoan.setBounds(20, 400, 300, 22);

        JScrollPane scrollLoan = new JScrollPane(tableLoan);
        scrollLoan.setBounds(20, 426, 930, 200);
        tableLoan.setRowHeight(22);

        add(lblTitle); add(lblFilter); add(tfSearch);
        add(scrollMember);
        add(btnTambah); add(btnEdit); add(btnHapus); add(btnDetail); add(btnBack);
        add(lblLoan); add(scrollLoan);

        loadData();

        tfSearch.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e)  { applyFilter(); }
            public void removeUpdate(DocumentEvent e)  { applyFilter(); }
            public void changedUpdate(DocumentEvent e) { applyFilter(); }
        });

        btnTambah.addActionListener(e -> new InputMemberDialog(this));

        btnEdit.addActionListener(e -> {
            int row = getSelectedModelRow(); if (row < 0) return;
            int    id     = (int) tableMember.getModel().getValueAt(row, 0);
            String name   =       tableMember.getModel().getValueAt(row, 1).toString();
            String email  =       tableMember.getModel().getValueAt(row, 2).toString();
            String status =       tableMember.getModel().getValueAt(row, 3).toString();
            new EditMemberDialog(this, id, name, email, status);
        });

        btnHapus.addActionListener(e -> {
            int row = getSelectedModelRow(); if (row < 0) return;
            int    id   = (int) tableMember.getModel().getValueAt(row, 0);
            String name =       tableMember.getModel().getValueAt(row, 1).toString();
            ctrl.delete(id, name, this::loadData);
        });

        btnDetail.addActionListener(e -> {
            int row = getSelectedModelRow(); if (row < 0) return;
            int id = (int) tableMember.getModel().getValueAt(row, 0);
            ctrl.loadLoanByMember(id, tableLoan);
        });

        btnBack.addActionListener(e -> { dispose(); new EmployeeDashboard(); });

        setVisible(true);
    }

    public void loadData() {
        ctrl.loadAll(tableMember);
        SwingUtilities.invokeLater(() -> {
            if (tableMember.getRowSorter() != null)
                sorter = (TableRowSorter<ModelTableMember>) tableMember.getRowSorter();
        });
    }

    private void applyFilter() {
        if (sorter == null) return;
        String t = tfSearch.getText().trim();
        sorter.setRowFilter(t.isEmpty() ? null :
            RowFilter.regexFilter("(?i)" + Pattern.quote(t)));
    }

    private int getSelectedModelRow() {
        int row = tableMember.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih member terlebih dahulu!",
                "Peringatan", JOptionPane.WARNING_MESSAGE);
            return -1;
        }
        return tableMember.convertRowIndexToModel(row);
    }

    private void styleBtn(JButton b) {
        b.setBackground(Color.BLACK);
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Arial", Font.BOLD, 11));
        b.setFocusPainted(false);
        b.setBorderPainted(false);
    }
}
