/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Admin.DAOAdmin;
import Model.Admin.InterfaceDAOAdmin;
import View.LoginView;
import View.Rack.ViewRack;

import javax.swing.JOptionPane;

public class ControllerLogin {

    LoginView halamanLogin;

    InterfaceDAOAdmin daoAdmin;

    public ControllerLogin(LoginView halamanLogin) {

        this.halamanLogin = halamanLogin;

        this.daoAdmin = new DAOAdmin();
    }

    public void login() {

        try {

            String username =
            halamanLogin.getInputUsername();

            String password =
            halamanLogin.getInputPassword();

            if ("".equals(username) || "".equals(password)) {

                throw new Exception(
                "Username atau Password tidak boleh kosong!"
                );
            }

            boolean status =
            daoAdmin.login(username, password);

            if (status) {

                JOptionPane.showMessageDialog(
                null,
                "Login Berhasil"
                );

                halamanLogin.dispose();

                new ViewRack();

            } else {

                JOptionPane.showMessageDialog(
                null,
                "Username atau Password salah!"
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
            null,
            e.getMessage()
            );
        }
    }
}
