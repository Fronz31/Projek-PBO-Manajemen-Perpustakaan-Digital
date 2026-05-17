/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Rack;
import Model.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAORack implements InterfaceDAORack{
    Connection conn;

    public DAORack() {

        conn = Connector.connection();
    }

    @Override
    public void insert(ModelRack rack) {

        try {

            String query = "INSERT INTO rack(rack_name, category, location) VALUES (?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, rack.getRackName());
            stmt.setString(2, rack.getCategory());
            stmt.setString(3, rack.getLocation());

            stmt.executeUpdate();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public void update(ModelRack rack) {

        try {

            String query = "UPDATE rack SET rack_name=?, category=?, location=? WHERE id_rack=?";

            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, rack.getRackName());
            stmt.setString(2, rack.getCategory());
            stmt.setString(3, rack.getLocation());
            stmt.setInt(4, rack.getIdRack());

            stmt.executeUpdate();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public void delete(int id) {

        try {

            String query = "DELETE FROM rack WHERE id_rack=?";

            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public List<ModelRack> getAll() {

        List<ModelRack> listRack = new ArrayList<>();

        try {

            String query = "SELECT * FROM rack";

            PreparedStatement stmt = conn.prepareStatement(query);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                ModelRack rack = new ModelRack();

                rack.setIdRack(rs.getInt("id_rack"));
                rack.setRackName(rs.getString("rack_name"));
                rack.setCategory(rs.getString("category"));
                rack.setLocation(rs.getString("location"));

                listRack.add(rack);
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        return listRack;
    }
}
