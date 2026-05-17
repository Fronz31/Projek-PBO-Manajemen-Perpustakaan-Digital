/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Rack.*;
import View.Rack.*;

import java.util.List;
import javax.swing.JOptionPane;

public class ControllerRack {

    ViewRack halamanTable;
    InputRack halamanInput;
    EditRack halamanEdit;

    InterfaceDAORack daoRack;

    List<ModelRack> daftarRack;

    public ControllerRack(ViewRack halamanTable) {

        this.halamanTable = halamanTable;

        this.daoRack = new DAORack();
    }

    public ControllerRack(InputRack halamanInput) {

        this.halamanInput = halamanInput;

        this.daoRack = new DAORack();
    }

    public ControllerRack(EditRack halamanEdit) {

        this.halamanEdit = halamanEdit;

        this.daoRack = new DAORack();
    }

    public void showAllRack() {

        Thread t = new Thread(() -> {

            daftarRack = daoRack.getAll();

            ModelTableRack table =
            new ModelTableRack(daftarRack);

            halamanTable.getTableRack().setModel(table);
        });

        t.start();
    }

    public void insertRack() {

        try {

            ModelRack rackBaru = new ModelRack();

            String rackName =
            halamanInput.getInputRackName();

            String category =
            halamanInput.getInputCategory();

            String location =
            halamanInput.getInputLocation();

            if ("".equals(rackName)
            || "".equals(category)
            || "".equals(location)) {

                throw new Exception(
                "Semua data wajib diisi!"
                );
            }

            rackBaru.setRackName(rackName);
            rackBaru.setCategory(category);
            rackBaru.setLocation(location);

            daoRack.insert(rackBaru);

            JOptionPane.showMessageDialog(
            null,
            "Rack berhasil ditambahkan!"
            );

            halamanInput.dispose();

            new ViewRack();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
            null,
            e.getMessage()
            );
        }
    }

    public void editRack(int id) {

        try {

            ModelRack rackEdit = new ModelRack();

            String rackName =
            halamanEdit.getInputRackName();

            String category =
            halamanEdit.getInputCategory();

            String location =
            halamanEdit.getInputLocation();

            if ("".equals(rackName)
            || "".equals(category)
            || "".equals(location)) {

                throw new Exception(
                "Semua data wajib diisi!"
                );
            }

            rackEdit.setIdRack(id);
            rackEdit.setRackName(rackName);
            rackEdit.setCategory(category);
            rackEdit.setLocation(location);

            daoRack.update(rackEdit);

            JOptionPane.showMessageDialog(
            null,
            "Rack berhasil diupdate!"
            );

            halamanEdit.dispose();

            new ViewRack();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
            null,
            e.getMessage()
            );
        }
    }

    public void deleteRack(Integer baris) {

        Integer id =
        (int) halamanTable.getTableRack()
        .getValueAt(baris, 0);

        String rackName =
        halamanTable.getTableRack()
        .getValueAt(baris, 1).toString();

        int input = JOptionPane.showConfirmDialog(
        null,
        "Hapus " + rackName + "?",
        "Hapus Rack",
        JOptionPane.YES_NO_OPTION
        );

        if (input == 0) {

            daoRack.delete(id);

            JOptionPane.showMessageDialog(
            null,
            "Rack berhasil dihapus!"
            );

            showAllRack();
        }
    }
}
