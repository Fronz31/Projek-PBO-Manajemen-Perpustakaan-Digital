package View.Member;

import Controller.ControllerLoan;
import Controller.SessionManager;
import Model.Loan.ModelTableLoan;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;

public class ViewLoanMember extends JFrame {

    private final JTable  tableLoan  = new JTable();
    private final JButton btnKembali = new JButton("Kembalikan Buku");
    private final JButton btnBack    = new JButton("Kembali");

    private final ControllerLoan ctrl = new ControllerLoan();

    public ViewLoanMember() {
        String name     = SessionManager.getInstance().getMember().getName();
        int    idMember = SessionManager.getInstance().getMember().getIdMember();

        setTitle("Daftar Pinjaman - " + name);
        setSize(900, 520);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 250, 245));

        JLabel lblTitle = new JLabel("Buku yang Kamu Pinjam");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 15));
        lblTitle.setBounds(20, 12, 400, 28);

        JLabel lblHint = new JLabel(
            "Pilih buku yang belum dikembalikan lalu klik \"Kembalikan Buku\".");
        lblHint.setFont(new Font("Arial", Font.ITALIC, 11));
        lblHint.setForeground(new Color(80, 80, 80));
        lblHint.setBounds(20, 40, 800, 18);

        JScrollPane scroll = new JScrollPane(tableLoan);
        scroll.setBounds(20, 70, 850, 390);
        tableLoan.setRowHeight(24);

        btnKembali.setBounds(20,  475, 160, 32);
        btnBack   .setBounds(760, 475, 110, 32);

        styleBtn(btnKembali);
        styleBtn(btnBack);

        add(lblTitle); add(lblHint);
        add(scroll);
        add(btnKembali); add(btnBack);

        loadData(idMember);

        btnKembali.addActionListener(e -> {
            int row = tableLoan.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Pilih buku terlebih dahulu!",
                    "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int modelRow = tableLoan.convertRowIndexToModel(row);
            ModelTableLoan model = (ModelTableLoan) tableLoan.getModel();
            var loan = model.getLoan(modelRow);

            if (loan.sudahDikembalikan()) {
                JOptionPane.showMessageDialog(this,
                    "Buku ini sudah dikembalikan!",
                    "Info", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            ctrl.kembalikan(loan.getIdLoan(), loan.getIdBook(),
                            loan.getJudulBook(), () -> loadData(idMember));
        });

        btnBack.addActionListener(e -> { dispose(); new MemberDashboard(); });

        setVisible(true);
    }

    private void loadData(int idMember) {
        ctrl.loadByMember(idMember, tableLoan);
        SwingUtilities.invokeLater(() -> {
            if (tableLoan.getRowSorter() == null && tableLoan.getModel() != null) {
                tableLoan.setRowSorter(new TableRowSorter<>(tableLoan.getModel()));
            }
        });
    }

    private void styleBtn(JButton b) {
        b.setBackground(Color.BLACK);
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Arial", Font.BOLD, 12));
        b.setFocusPainted(false);
        b.setBorderPainted(false);
    }
}
