/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View.Rack;

import Controller.ControllerRack;
import javax.swing.*;

public class InputRack extends JFrame{
    JLabel title = new JLabel("INPUT RACK");

    JLabel lRackName = new JLabel("Rack Name");
    JLabel lCategory = new JLabel("Category");
    JLabel lLocation = new JLabel("Location");

    JTextField tfRackName = new JTextField();
    JTextField tfCategory = new JTextField();
    JTextField tfLocation = new JTextField();

    JButton btnTambah = new JButton("Tambah");

    ControllerRack controller;

    public InputRack() {

        setTitle("Input Rack");
        setSize(400, 300);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(title);

        add(lRackName);
        add(lCategory);
        add(lLocation);

        add(tfRackName);
        add(tfCategory);
        add(tfLocation);

        add(btnTambah);

        title.setBounds(140, 20, 200, 30);

        lRackName.setBounds(30, 70, 100, 25);
        tfRackName.setBounds(140, 70, 180, 25);

        lCategory.setBounds(30, 110, 100, 25);
        tfCategory.setBounds(140, 110, 180, 25);

        lLocation.setBounds(30, 150, 100, 25);
        tfLocation.setBounds(140, 150, 180, 25);

        btnTambah.setBounds(130, 210, 100, 30);

        controller = new ControllerRack(this);

        btnTambah.addActionListener(e -> {

            controller.insertRack();
        });

        setVisible(true);
    }

    public String getInputRackName() {
        return tfRackName.getText();
    }

    public String getInputCategory() {
        return tfCategory.getText();
    }

    public String getInputLocation() {
        return tfLocation.getText();
    }
}
