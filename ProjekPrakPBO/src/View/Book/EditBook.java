/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.Book;

import Controller.ControllerBook;

import javax.swing.*;

public class EditBook extends JFrame {

    JLabel titleForm = new JLabel("EDIT BOOK");

    JLabel lTitle = new JLabel("Title");
    JLabel lAuthor = new JLabel("Author");
    JLabel lYear = new JLabel("Year");
    JLabel lStock = new JLabel("Stock");

    JTextField tfTitle = new JTextField();
    JTextField tfAuthor = new JTextField();
    JTextField tfYear = new JTextField();
    JTextField tfStock = new JTextField();

    JButton btnEdit = new JButton("Edit");

    ControllerBook controller;

    int idBook;
    int idRack;

    public EditBook(
    int idBook,
    String title,
    String author,
    int year,
    int stock,
    int idRack
    ) {

        this.idBook = idBook;
        this.idRack = idRack;

        setTitle("Edit Book");
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

        add(btnEdit);

        titleForm.setBounds(140, 20, 200, 30);

        lTitle.setBounds(30, 70, 100, 25);
        tfTitle.setBounds(140, 70, 180, 25);

        lAuthor.setBounds(30, 110, 100, 25);
        tfAuthor.setBounds(140, 110, 180, 25);

        lYear.setBounds(30, 150, 100, 25);
        tfYear.setBounds(140, 150, 180, 25);

        lStock.setBounds(30, 190, 100, 25);
        tfStock.setBounds(140, 190, 180, 25);

        btnEdit.setBounds(130, 250, 100, 30);

        tfTitle.setText(title);
        tfAuthor.setText(author);
        tfYear.setText(String.valueOf(year));
        tfStock.setText(String.valueOf(stock));

        controller = new ControllerBook(this);

        btnEdit.addActionListener(e -> {

            controller.editBook(idBook, idRack);
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
