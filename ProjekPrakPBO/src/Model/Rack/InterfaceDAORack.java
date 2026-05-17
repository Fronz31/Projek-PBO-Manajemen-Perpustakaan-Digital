/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Rack;
import java.util.List;

public interface InterfaceDAORack {
    public void insert(ModelRack rack);

    public void update(ModelRack rack);

    public void delete(int id);

    public List<ModelRack> getAll();
}
