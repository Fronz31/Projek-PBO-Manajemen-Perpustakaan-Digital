/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Rack;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelTableRack extends AbstractTableModel{
    List<ModelRack> listRack;

    String[] columnName = {
        "ID",
        "Rack Name",
        "Category",
        "Location"
    };

    public ModelTableRack(List<ModelRack> listRack) {

        this.listRack = listRack;
    }

    @Override
    public int getRowCount() {
        return listRack.size();
    }

    @Override
    public int getColumnCount() {
        return columnName.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex) {

            case 0:
                return listRack.get(rowIndex).getIdRack();

            case 1:
                return listRack.get(rowIndex).getRackName();

            case 2:
                return listRack.get(rowIndex).getCategory();

            case 3:
                return listRack.get(rowIndex).getLocation();

            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnName[column];
    }
}
