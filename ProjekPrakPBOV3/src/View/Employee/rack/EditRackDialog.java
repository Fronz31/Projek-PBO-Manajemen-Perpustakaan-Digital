package View.Employee.rack;

import Controller.ControllerRack;
import javax.swing.*;
import java.awt.*;

public class EditRackDialog extends JDialog {

    private final JTextField tfName;
    private final JTextField tfCat;
    private final JTextField tfLoc;

    public EditRackDialog(ViewRackEmployee parent, int id, String name, String cat, String loc) {
        super(parent, "Edit Rak", true);
        setSize(380, 240); setLayout(null); setLocationRelativeTo(parent);

        JLabel l1 = new JLabel("Nama Rak:"); l1.setBounds(30, 30, 100, 25); add(l1);
        JLabel l2 = new JLabel("Kategori:"); l2.setBounds(30, 70, 100, 25); add(l2);
        JLabel l3 = new JLabel("Lokasi:");   l3.setBounds(30,110, 100, 25); add(l3);

        tfName = new JTextField(name); tfName.setBounds(140, 30, 200, 25); add(tfName);
        tfCat  = new JTextField(cat);  tfCat .setBounds(140, 70, 200, 25); add(tfCat);
        tfLoc  = new JTextField(loc);  tfLoc .setBounds(140,110, 200, 25); add(tfLoc);

        JButton btnSave = new JButton("Update");
        btnSave.setBounds(130, 165, 100, 32);
        btnSave.setBackground(Color.BLACK);
        btnSave.setForeground(Color.WHITE);
        btnSave.setFont(new Font("Arial", Font.BOLD, 12));
        btnSave.setFocusPainted(false);
        btnSave.setBorderPainted(false);
        btnSave.setOpaque(true);
        add(btnSave);

        ControllerRack ctrl = new ControllerRack();
        btnSave.addActionListener(e ->
            ctrl.update(id, tfName.getText(), tfCat.getText(), tfLoc.getText(), () -> {
                dispose(); parent.loadRack();
            })
        );

        setVisible(true);
    }
}
