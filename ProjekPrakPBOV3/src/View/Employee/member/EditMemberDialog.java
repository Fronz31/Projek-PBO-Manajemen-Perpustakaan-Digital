package View.Employee.member;

import Controller.ControllerMember;
import javax.swing.*;
import java.awt.*;

public class EditMemberDialog extends JDialog {

    public EditMemberDialog(ViewMemberEmployee parent, int id,
                             String name, String email, String status) {
        super(parent, "Edit Member", true);
        setSize(400, 310); setLayout(null); setLocationRelativeTo(parent);

        JTextField tfName  = new JTextField(name);  tfName .setBounds(140, 30, 210, 25); add(tfName);
        JTextField tfEmail = new JTextField(email); tfEmail.setBounds(140, 70, 210, 25); add(tfEmail);
        JPasswordField tfPass = new JPasswordField(); tfPass.setBounds(140,110, 210, 25); add(tfPass);

        JLabel hint = new JLabel("(kosongkan jika tidak ganti)");
        hint.setFont(new Font("Arial", Font.ITALIC, 10));
        hint.setForeground(Color.GRAY);
        hint.setBounds(140, 133, 220, 15); add(hint);

        String[] statuses = {"aktif", "non-aktif"};
        JComboBox<String> cbStatus = new JComboBox<>(statuses);
        cbStatus.setSelectedItem(status);
        cbStatus.setBounds(140, 152, 210, 25); add(cbStatus);

        JLabel l1 = new JLabel("Nama:");     l1.setBounds(30, 30,100,25); add(l1);
        JLabel l2 = new JLabel("Email:");    l2.setBounds(30, 70,100,25); add(l2);
        JLabel l3 = new JLabel("Password:"); l3.setBounds(30,110,100,25); add(l3);
        JLabel l4 = new JLabel("Status:");   l4.setBounds(30,152,100,25); add(l4);

        JButton btnSave = new JButton("Update");
        btnSave.setBounds(130, 210, 110, 32);
        btnSave.setBackground(Color.BLACK);
        btnSave.setForeground(Color.WHITE);
        btnSave.setFont(new Font("Arial", Font.BOLD, 12));
        btnSave.setFocusPainted(false);
        btnSave.setBorderPainted(false);
        btnSave.setOpaque(true);
        add(btnSave);

        ControllerMember ctrl = new ControllerMember();
        btnSave.addActionListener(e ->
            ctrl.update(id, tfName.getText(), tfEmail.getText(),
                        new String(tfPass.getPassword()),
                        cbStatus.getSelectedItem().toString(), () -> {
                dispose(); parent.loadData();
            })
        );

        setVisible(true);
    }
}
