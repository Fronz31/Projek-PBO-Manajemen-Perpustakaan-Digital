/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Book;

import java.util.List;

public interface InterfaceDAOBook {
    public void insert(ModelBook book);

    public void update(ModelBook book);

    public void delete(int id);

    public List<ModelBook> getAll(int idRack);
}
