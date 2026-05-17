/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Book;

import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelTableBook extends AbstractTableModel{
    List<ModelBook> listBook;

    String[] columnName = {
        "ID",
        "Title",
        "Author",
        "Year",
        "Stock"
    };

    public ModelTableBook(List<ModelBook> listBook) {

        this.listBook = listBook;
    }

    @Override
    public int getRowCount() {
        return listBook.size();
    }

    @Override
    public int getColumnCount() {
        return columnName.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex) {

            case 0:
                return listBook.get(rowIndex).getIdBook();

            case 1:
                return listBook.get(rowIndex).getTitle();

            case 2:
                return listBook.get(rowIndex).getAuthor();

            case 3:
                return listBook.get(rowIndex).getYear();

            case 4:
                return listBook.get(rowIndex).getStock();

            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnName[column];
    }
}
