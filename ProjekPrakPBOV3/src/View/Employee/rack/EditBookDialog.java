package View.Employee.rack;

import Controller.ControllerBook;
import javax.swing.*;
import java.awt.*;

public class EditBookDialog extends JDialog {

    public EditBookDialog(ViewBookEmployee parent, int idBook,
                          String title, String author, int year, int stock, int idRack) {
        super(parent, "Edit Buku", true);
        setSize(400, 300); setLayout(null); setLocationRelativeTo(parent);

        JTextField tfTitle  = new JTextField(title);           tfTitle .setBounds(140, 30, 210, 25); add(tfTitle);
        JTextField tfAuthor = new JTextField(author);          tfAuthor.setBounds(140, 70, 210, 25); add(tfAuthor);
        JTextField tfYear   = new JTextField("" + year);       tfYear  .setBounds(140,110, 210, 25); add(tfYear);
        JTextField tfStock  = new JTextField("" + stock);      tfStock .setBounds(140,150, 210, 25); add(tfStock);

        JLabel l1 = new JLabel("Judul:");      l1.setBounds(30, 30, 100, 25); add(l1);
        JLabel l2 = new JLabel("Pengarang:");  l2.setBounds(30, 70, 100, 25); add(l2);
        JLabel l3 = new JLabel("Tahun:");      l3.setBounds(30,110, 100, 25); add(l3);
        JLabel l4 = new JLabel("Stok:");       l4.setBounds(30,150, 100, 25); add(l4);

        JButton btnSave = new JButton("Update");
        btnSave.setBounds(140, 205, 100, 32);
        btnSave.setBackground(Color.BLACK);
        btnSave.setForeground(Color.WHITE);
        btnSave.setFont(new Font("Arial", Font.BOLD, 12));
        btnSave.setFocusPainted(false);
        btnSave.setBorderPainted(false);
        btnSave.setOpaque(true);
        add(btnSave);

        ControllerBook ctrl = new ControllerBook();
        btnSave.addActionListener(e ->
            ctrl.update(idBook, tfTitle.getText(), tfAuthor.getText(),
                        tfYear.getText(), tfStock.getText(), idRack, () -> {
                dispose(); parent.loadBook();
            })
        );

        setVisible(true);
    }
}
