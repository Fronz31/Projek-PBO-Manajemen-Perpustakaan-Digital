/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.Book;

import Controller.ControllerBook;
import View.Rack.ViewRack;
import javax.swing.*;

public class ViewBook extends JFrame {

    JTable tableBook = new JTable();

    JScrollPane scrollPane = new JScrollPane(tableBook);

    JButton btnTambah = new JButton("Tambah");
    JButton btnEdit = new JButton("Edit");
    JButton btnHapus = new JButton("Hapus");
    JButton btnBack = new JButton("Back");

    ControllerBook controller;

    int idRack;

    public ViewBook(int idRack) {

        this.idRack = idRack;

        setTitle("Book Management");
        setSize(900, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(scrollPane);

        add(btnTambah);
        add(btnEdit);
        add(btnHapus);
        add(btnBack);

        scrollPane.setBounds(20, 20, 840, 350);

        btnTambah.setBounds(20, 390, 100, 30);
        btnEdit.setBounds(140, 390, 100, 30);
        btnHapus.setBounds(260, 390, 100, 30);
        btnBack.setBounds(380, 390, 100, 30);

        controller = new ControllerBook(this);

        controller.showAllBook(idRack);

        btnTambah.addActionListener(e -> {

            new InputBook(idRack);

            dispose();
        });

        btnEdit.addActionListener(e -> {

            int baris = tableBook.getSelectedRow();

            if (baris != -1) {

                int idBook =
                (int) tableBook.getValueAt(baris, 0);

                String title =
                tableBook.getValueAt(baris, 1).toString();

                String author =
                tableBook.getValueAt(baris, 2).toString();

                int year =
                (int) tableBook.getValueAt(baris, 3);

                int stock =
                (int) tableBook.getValueAt(baris, 4);

                new EditBook(
                idBook,
                title,
                author,
                year,
                stock,
                idRack
                );

                dispose();
            }
        });

        btnHapus.addActionListener(e -> {

            int baris = tableBook.getSelectedRow();

            if (baris != -1) {

                controller.deleteBook(baris);
            }
        });

        btnBack.addActionListener(e -> {

            new ViewRack();

            dispose();
        });

        setVisible(true);
    }

    public JTable getTableBook() {
        return tableBook;
    }

    public int getIdRack() {
        return idRack;
    }
}
