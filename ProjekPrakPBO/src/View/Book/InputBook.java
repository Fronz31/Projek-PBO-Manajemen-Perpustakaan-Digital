/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.Book;

import Controller.ControllerBook;

import javax.swing.*;

public class InputBook extends JFrame {

    JLabel titleForm = new JLabel("INPUT BOOK");

    JLabel lTitle = new JLabel("Title");
    JLabel lAuthor = new JLabel("Author");
    JLabel lYear = new JLabel("Year");
    JLabel lStock = new JLabel("Stock");

    JTextField tfTitle = new JTextField();
    JTextField tfAuthor = new JTextField();
    JTextField tfYear = new JTextField();
    JTextField tfStock = new JTextField();

    JButton btnTambah = new JButton("Tambah");

    ControllerBook controller;

    int idRack;

    public InputBook(int idRack) {

        this.idRack = idRack;

        setTitle("Input Book");
        setSize(400, 350);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(titleForm);

        add(lTitle);
        add(lAuthor);
        add(lYear);
        add(lStock);

        add(tfTitle);
        add(tfAuthor);
        add(tfYear);
        add(tfStock);

        add(btnTambah);

        titleForm.setBounds(140, 20, 200, 30);

        lTitle.setBounds(30, 70, 100, 25);
        tfTitle.setBounds(140, 70, 180, 25);

        lAuthor.setBounds(30, 110, 100, 25);
        tfAuthor.setBounds(140, 110, 180, 25);

        lYear.setBounds(30, 150, 100, 25);
        tfYear.setBounds(140, 150, 180, 25);

        lStock.setBounds(30, 190, 100, 25);
        tfStock.setBounds(140, 190, 180, 25);

        btnTambah.setBounds(130, 250, 100, 30);

        controller = new ControllerBook(this);

        btnTambah.addActionListener(e -> {

            controller.insertBook(idRack);
        });

        setVisible(true);
    }

    public String getInputTitle() {
        return tfTitle.getText();
    }

    public String getInputAuthor() {
        return tfAuthor.getText();
    }

    public String getInputYear() {
        return tfYear.getText();
    }

    public String getInputStock() {
        return tfStock.getText();
    }
}
