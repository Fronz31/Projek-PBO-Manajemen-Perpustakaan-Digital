package View.Employee.member;

import Controller.ControllerMember;
import javax.swing.*;
import java.awt.*;

public class InputMemberDialog extends JDialog {

    public InputMemberDialog(ViewMemberEmployee parent) {
        super(parent, "Tambah Member", true);
        setSize(400, 270); setLayout(null); setLocationRelativeTo(parent);

        JTextField tfName  = new JTextField(); tfName .setBounds(140, 30, 210, 25); add(tfName);
        JTextField tfEmail = new JTextField(); tfEmail.setBounds(140, 70, 210, 25); add(tfEmail);
        JPasswordField tfPass = new JPasswordField(); tfPass.setBounds(140,110, 210, 25); add(tfPass);

        JLabel l1 = new JLabel("Nama:");     l1.setBounds(30, 30, 100, 25); add(l1);
        JLabel l2 = new JLabel("Email:");    l2.setBounds(30, 70, 100, 25); add(l2);
        JLabel l3 = new JLabel("Password:"); l3.setBounds(30,110, 100, 25); add(l3);

        JButton btnSave = new JButton("Daftarkan");
        btnSave.setBounds(130, 165, 120, 32);
        btnSave.setBackground(Color.BLACK);
        btnSave.setForeground(Color.WHITE);
        btnSave.setFont(new Font("Arial", Font.BOLD, 12));
        btnSave.setFocusPainted(false);
        btnSave.setBorderPainted(false);
        btnSave.setOpaque(true);
        add(btnSave);

        ControllerMember ctrl = new ControllerMember();
        btnSave.addActionListener(e ->
            ctrl.insert(tfName.getText(), tfEmail.getText(),
                        new String(tfPass.getPassword()), () -> {
                dispose(); parent.loadData();
            })
        );

        setVisible(true);
    }
}
