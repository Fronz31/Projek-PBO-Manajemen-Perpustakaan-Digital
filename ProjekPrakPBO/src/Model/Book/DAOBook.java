/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Book;

import Model.Connector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DAOBook implements InterfaceDAOBook{
    Connection conn;

    public DAOBook() {
        conn = Connector.connection();
    }

    @Override
    public void insert(ModelBook book) {

        try {

            String query =
            "INSERT INTO book(title, author, year, stock, id_rack) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setInt(3, book.getYear());
            stmt.setInt(4, book.getStock());
            stmt.setInt(5, book.getIdRack());

            stmt.executeUpdate();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    @Override
    public void update(ModelBook book) {

        try {

            String query =
            "UPDATE book SET title=?, author=?, year=?, stock=? WHERE id_book=?";

            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setInt(3, book.getYear());
            stmt.setInt(4, book.getStock());
            stmt.setInt(5, book.getIdBook());

            stmt.executeUpdate();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {

        try {

            String query =
            "DELETE FROM book WHERE id_book=?";

            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, id);

            stmt.executeUpdate();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<ModelBook> getAll(int idRack) {

        List<ModelBook> listBook = new ArrayList<>();

        try {

            String query =
            "SELECT * FROM book WHERE id_rack=?";

            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setInt(1, idRack);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                ModelBook book = new ModelBook();

                book.setIdBook(rs.getInt("id_book"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setYear(rs.getInt("year"));
                book.setStock(rs.getInt("stock"));
                book.setIdRack(rs.getInt("id_rack"));

                listBook.add(book);
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        return listBook;
    }
}
