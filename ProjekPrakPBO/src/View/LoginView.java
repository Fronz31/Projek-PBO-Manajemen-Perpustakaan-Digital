/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package View;

import Controller.ControllerLogin;
import javax.swing.*;

public class LoginView extends JFrame{
    JLabel title = new JLabel("LOGIN ADMIN");

    JLabel lUsername = new JLabel("Username");
    JLabel lPassword = new JLabel("Password");

    JTextField tfUsername = new JTextField();
    JPasswordField tfPassword = new JPasswordField();

    JButton btnLogin = new JButton("Login");

    ControllerLogin controller;

    public LoginView() {

        setTitle("Login");
        setSize(350, 250);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        add(title);
        add(lUsername);
        add(lPassword);
        add(tfUsername);
        add(tfPassword);
        add(btnLogin);

        title.setBounds(120, 20, 200, 30);

        lUsername.setBounds(40, 70, 100, 25);
        tfUsername.setBounds(130, 70, 150, 25);

        lPassword.setBounds(40, 110, 100, 25);
        tfPassword.setBounds(130, 110, 150, 25);

        btnLogin.setBounds(120, 160, 100, 30);

        controller = new ControllerLogin(this);

        btnLogin.addActionListener(e -> {

            controller.login();
        });

        setVisible(true);
    }

    public String getInputUsername() {
        return tfUsername.getText();
    }

    public String getInputPassword() {
        return tfPassword.getText();
    }
}
