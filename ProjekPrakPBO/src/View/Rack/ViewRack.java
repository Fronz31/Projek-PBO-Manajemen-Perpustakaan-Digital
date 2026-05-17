/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.Rack;

import Controller.ControllerRack;
import View.Book.ViewBook;
import javax.swing.*;

public class ViewRack extends JFrame{
    JTable tableRack = new JTable();

    JScrollPane scrollPane = new JScrollPane(tableRack);

    JButton btnTambah = new JButton("Tambah");
    JButton btnEdit = new JButton("Edit");
    JButton btnHapus = new JButton("Hapus");
    JButton btnBuka = new JButton("Buka Rack");

    ControllerRack controller;

    public ViewRack() {

        setTitle("Rack Management");
        setSize(800, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(scrollPane);

        add(btnTambah);
        add(btnEdit);
        add(btnHapus);
        add(btnBuka);

        scrollPane.setBounds(20, 20, 740, 350);

        btnTambah.setBounds(20, 390, 100, 30);
        btnEdit.setBounds(140, 390, 100, 30);
        btnHapus.setBounds(260, 390, 100, 30);
        btnBuka.setBounds(380, 390, 120, 30);

        controller = new ControllerRack(this);

        controller.showAllRack();

        btnTambah.addActionListener(e -> {

            new InputRack();

            dispose();
        });

        btnEdit.addActionListener(e -> {

            int baris = tableRack.getSelectedRow();

            if (baris != -1) {

                int id =
                (int) tableRack.getValueAt(baris, 0);

                String rackName =
                tableRack.getValueAt(baris, 1).toString();

                String category =
                tableRack.getValueAt(baris, 2).toString();

                String location =
                tableRack.getValueAt(baris, 3).toString();

                new EditRack(
                id,
                rackName,
                category,
                location
                );

                dispose();
            }
        });

        btnHapus.addActionListener(e -> {

            int baris = tableRack.getSelectedRow();

            if (baris != -1) {

                controller.deleteRack(baris);
            }
        });

        btnBuka.addActionListener(e -> {

            int baris = tableRack.getSelectedRow();

            if (baris != -1) {

                int idRack =
                (int) tableRack.getValueAt(baris, 0);

                new ViewBook(idRack);

                dispose();
            }
        });

        setVisible(true);
    }

    public JTable getTableRack() {
        return tableRack;
    }
}
