package View.Member;

import Controller.SessionManager;
import View.Auth.LoginView;

import javax.swing.*;
import java.awt.*;

public class MemberDashboard extends JFrame {

    public MemberDashboard() {
        String name = SessionManager.getInstance().getMember().getName();

        setTitle("Dashboard Member - " + name);
        setSize(480, 360);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 250, 245));

        JLabel lblWelcome = new JLabel("Selamat datang, " + name + "!", SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 16));
        lblWelcome.setBounds(0, 35, 480, 30);

        JLabel lblSub = new JLabel("Pilih menu:", SwingConstants.CENTER);
        lblSub.setFont(new Font("Arial", Font.PLAIN, 12));
        lblSub.setForeground(Color.DARK_GRAY);
        lblSub.setBounds(0, 68, 480, 20);

        JButton btnRack     = buatBtn("Jelajahi Rak & Pinjam Buku");
        JButton btnPinjaman = buatBtn("Daftar Buku yang Dipinjam");
        JButton btnLogout   = buatBtn("Logout");

        btnRack    .setBounds(90, 110, 300, 48);
        btnPinjaman.setBounds(90, 175, 300, 48);
        btnLogout  .setBounds(90, 265, 300, 36);

        add(lblWelcome); add(lblSub);
        add(btnRack); add(btnPinjaman); add(btnLogout);

        btnRack    .addActionListener(e -> { dispose(); new ViewRackMember(); });
        btnPinjaman.addActionListener(e -> { dispose(); new ViewLoanMember(); });
        btnLogout  .addActionListener(e -> {
            int ok = JOptionPane.showConfirmDialog(this, "Yakin ingin logout?",
                "Logout", JOptionPane.YES_NO_OPTION);
            if (ok == JOptionPane.YES_OPTION) {
                SessionManager.getInstance().logout();
                dispose();
                new LoginView();
            }
        });

        setVisible(true);
    }

    private JButton buatBtn(String text) {
        JButton b = new JButton(text);
        b.setBackground(Color.BLACK);
        b.setForeground(Color.WHITE);
        b.setFont(new Font("Arial", Font.BOLD, 13));
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        return b;
    }
}
