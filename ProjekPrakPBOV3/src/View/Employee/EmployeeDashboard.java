package View.Employee;

import Controller.SessionManager;
import View.Auth.LoginView;
import View.Employee.rack.ViewRackEmployee;
import View.Employee.employee.ViewEmployee;
import View.Employee.member.ViewMemberEmployee;

import javax.swing.*;
import java.awt.*;

public class EmployeeDashboard extends JFrame {

    public EmployeeDashboard() {
        String role    = SessionManager.getInstance().getEmployee().getRole();
        String uname   = SessionManager.getInstance().getEmployee().getUsername();
        boolean isMgr  = SessionManager.getInstance().isManager();

        setTitle("Dashboard Employee - " + uname + " (" + role + ")");
        setSize(500, 430);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 248, 255));

        JLabel lblWelcome = new JLabel("Selamat datang, " + uname + "!", SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 16));
        lblWelcome.setBounds(0, 30, 500, 30);

        JLabel lblRole = new JLabel("Role: " + role, SwingConstants.CENTER);
        lblRole.setFont(new Font("Arial", Font.ITALIC, 12));
        lblRole.setForeground(Color.DARK_GRAY);
        lblRole.setBounds(0, 60, 500, 20);

        JButton btnRack     = buatTombol("Manajemen Rak & Buku");
        JButton btnEmployee = buatTombol("Manajemen Pegawai");
        JButton btnMember   = buatTombol("Manajemen Member");
        JButton btnLogout   = buatTombol("Logout");

        btnRack    .setBounds(100, 110, 300, 45);
        btnEmployee.setBounds(100, 170, 300, 45);
        btnMember  .setBounds(100, 230, 300, 45);
        btnLogout  .setBounds(100, 300, 300, 35);

        if (!isMgr) {
            btnEmployee.setEnabled(false);
            btnEmployee.setToolTipText("Hanya Manager yang dapat mengakses menu ini");
        }

        add(lblWelcome); add(lblRole);
        add(btnRack); add(btnEmployee); add(btnMember); add(btnLogout);

        btnRack.addActionListener(e -> { dispose(); new ViewRackEmployee(); });
        btnEmployee.addActionListener(e -> { dispose(); new ViewEmployee(); });
        btnMember.addActionListener(e -> { dispose(); new ViewMemberEmployee(); });
        btnLogout.addActionListener(e -> {
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

    private JButton buatTombol(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Arial", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        return btn;
    }
}
