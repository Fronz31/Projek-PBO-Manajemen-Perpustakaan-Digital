/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Book.*;
import View.Book.*;
import java.util.List;
import javax.swing.JOptionPane;

public class ControllerBook {

    ViewBook halamanTable;
    InputBook halamanInput;
    EditBook halamanEdit;

    InterfaceDAOBook daoBook;

    List<ModelBook> daftarBook;

    public ControllerBook(ViewBook halamanTable) {

        this.halamanTable = halamanTable;

        this.daoBook = new DAOBook();
    }

    public ControllerBook(InputBook halamanInput) {

        this.halamanInput = halamanInput;

        this.daoBook = new DAOBook();
    }

    public ControllerBook(EditBook halamanEdit) {

        this.halamanEdit = halamanEdit;

        this.daoBook = new DAOBook();
    }

    public void showAllBook(int idRack) {

        Thread t = new Thread(() -> {

            daftarBook = daoBook.getAll(idRack);

            ModelTableBook table =
            new ModelTableBook(daftarBook);

            halamanTable.getTableBook().setModel(table);
        });

        t.start();
    }
}
