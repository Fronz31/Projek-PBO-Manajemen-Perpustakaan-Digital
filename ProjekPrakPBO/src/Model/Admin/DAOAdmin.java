/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Admin;
import Model.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DAOAdmin implements InterfaceDAOAdmin{
    Connection conn;

    public DAOAdmin() {

        conn = Connector.connection();
    }

    @Override
    public boolean login(String username, String password) {

        try {

            String query = "SELECT * FROM admin WHERE username=? AND password=?";

            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            return rs.next();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        return false;
    }
}
